package exercises

import javafx.collections.transformation.SortedList

//Praticamente Function types já

//trait MyPredicate[-T] {               // T => Boolean
//    def test(elem: T): Boolean
//}
//
//trait MyTransformer[-A,B] {           // A => B
//    def transform(element: A): B
//}

abstract class MyList[+A]{

    //Singly linked list that hold integers

    /*
    head => int ; first element of this list
    tail => MyList ; last element of this list
    isEmpty => Boolean
    add(int) => MyList; new list with this element added
    override toString => String ; string representation of the list
     */


    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A] (element: B): MyList[B]
    def printElements: String
    override def toString: String = "[" + printElements + "]"

    //ESSAS 3 EM BAIXO SÃO HIGHER-ORDER FUNCTIONS
    //High-Order Functions recebem functions como parâmetros ou retornam outras funções
    def map[B](transformer: A => B): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]

    def foreach(f:A => Unit): Unit
    def sort(compare:(A,A) => Int): MyList[A]                  //Retorna negativo se o primeiro for menor que o segundo, positivo caso contrário
    def zipWith[B,C](myList: MyList[B], zipper: (A,B) => C): MyList[C]
    def fold[B](start: B)(f: (B,A) => B): B

    //Concatenação
    def ++[B >: A](list: MyList[B]): MyList[B]
}

case object Empty extends MyList[Nothing]{
    override def head: Nothing = throw new NoSuchElementException
    override def tail: MyList[Nothing] = throw new NoSuchElementException

    override def isEmpty: Boolean = true

    override def add[B >: Nothing](element: B): MyList[B] = new Node(element,Empty)

    override def printElements: String = ""

    override def map[B](transformer: Nothing => B): MyList[B] = Empty

    override def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    override def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty

    override def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

    override def foreach(f: Nothing => Unit): Unit = ()

    override def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty


    override def zipWith[B, C](myList: MyList[B], zipper: (Nothing, B) => C): MyList[C] = {
        if (! myList.isEmpty) throw new RuntimeException("Lists do not have the same length")
        else Empty
    }

    override def fold[B](start: B)(f: (B, Nothing) => B): B = start
}

//É uma pilha
case class Node[+A](h: A, t: MyList[A]) extends MyList[A] {
    override def head: A = h

    override def tail: MyList[A] = t

    override def isEmpty: Boolean = false

    override def add[B >: A](element: B): MyList[B] = new Node(element,this)

    override def printElements: String =
        if(t.isEmpty) ""+h
        else h + " " + tail.printElements

    /*
    [1,2,3].map(n ** n)
    = new Node(1, [2,3].map(n*n))
    = new Node(1,new Node(4,[3].map(n*n)))
    = new Node(1,new Node(4,new Node(9,Empty.map(n*n))))
    = new Node(1,new Node(4,new Node(9,Empty))))
     */

    override def map[B](transformer: A => B): MyList[B] = {
        new Node(transformer(this.h),t.map(transformer))      //Recursivo

    }

    /*
    [1,2,3].filter(n % 2 == 0)
     = [2,3].filter(n % 2 == 0)
     = new Node(2,[3].filter(n % 2 == 0))
     = new Node(2,Empty.filter(n % 2 == 0))
     = new Node(2,Empty)
     */

    override def filter(predicate: A => Boolean): MyList[A] = {
        if (predicate(this.h))                                  //Chamando o apply da Function1
            new Node(this.h,t.filter(predicate))                //Recursivo
        else
            t.filter(predicate)                                 //Recursivo
    }

    /*
   [1,2] ++ [3,4,5]
   = new Node(1,[2] ++ [3,4,5])
   = new Node(1,new Node(2,Empty ++ [3,4,5]))
   = new Node(1,new Node(2,[3,4,5]))
    */
    override def ++[B >: A](list: MyList[B]): MyList[B] = {
        new Node(h,t ++ list)                                   //Recursivo, vai fazer até chegar numa tail Empty, que vai tentar concatenar
        // em nada e acabar retornando a própria lista
    }

    /*
    [1,2,3].flatMap(n => [n,n+1])
    = [1,2] ++ [2,3].flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ [3].flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ [3,4] + Empty.flatMap(n => [n,n+1])
    = [1,2] ++ [2,3] ++ [3,4] ++ Empty
    = [1,2,2,3,3,4]
     */

    override def flatMap[B](transformer: A => MyList[B]): MyList[B] = {
        transformer(this.h) ++ this.t.flatMap(transformer)                    //++ já retorna uma lista
    }

    override def foreach(f: A => Unit): Unit = {
        f(this.h)
        this.t.foreach(f)

    }

    //O compare Retorna negativo se o primeiro for menor que o segundo, positivo caso contrário
    override def sort(compare: (A, A) => Int): MyList[A] = {
        def insert(x: A, sortedList: MyList[A]): MyList[A] = {
            if (sortedList.isEmpty)
                Node(x,Empty)                                   //Apply do companion Object
            else if (compare(x,sortedList.head) <= 0)
                Node(x,sortedList)
            else
                Node(sortedList.head,insert(x,sortedList.tail))
        }

        val sortedTail = this.t.sort(compare)
        insert(this.h,sortedTail)

    }



    override def zipWith[B, C](myList: MyList[B], zipper: (A, B) => C): MyList[C] = {
        if (myList.isEmpty) throw new RuntimeException("Lists do not have the same length")
        else Node(zipper(this.h,myList.head), this.t.zipWith(myList.tail,zipper))
    }

    /*
        [1,2,3].fold(0)(+) =
        = [2,3].fold(0+1)(+) =
        = [3].fold(1+2)(+) =
        = Empty.fold(3+3)(+) =
        6

     */

    override def fold[B](start: B)(operator: (B, A) => B): B = {
        this.t.fold(operator(start,this.h))(operator)
    }
}





 /*
 1. Generic trait MyPredicate[-T]:
     método => Testa se um valor de tipo T satisfaz uma condição, cada subclasse vai ter uma implementação
     deferente desse método

 2. Generic trait MyTransformer[-A,B]:
     método => converter um valor do tipo A em tipo B, cada subclasse vai ter uma implementação
     deferente desse método

 3. MyList:
    método  => map(transformer: MyTransformer) e retorna MyList de um tipo diferente
    método  => filter(predicate: MyPredicate) e retorna MyList que satisfaz ele
    método  => flatMap(transformer: MyTransformer[A,MyList[B]) e retorna MyList[B]

 Ex:
    class EvenPredicate extends MyPredicate[Int] => usaria a função test do trait
    class StringToIntTransformer extends MyTransformer[String,Int]   => usaria a função transform do trait

 Ex:
    [1,2,3].map(n*2) = [2,4,6]
    [1,2,3,4].filter(n % 2 == 0) = [2,4]
    [1,2,3].flatMap(n => [n,n+1]) = [1,2,2,3,3,4]


  */



object ListTest extends App{


    val listIntegers:MyList[Int] = new Node(1, new Node(2, new Node(3,Empty)))
    val otherListIntegers:MyList[Int] = new Node(13, new Node(4, new Node(-2,Empty)))

    val forCombinations = for{
        i <- listIntegers
        j <- otherListIntegers
    }yield i+":"+j

    println(forCombinations)

    val mapCombinations = listIntegers.flatMap(i => otherListIntegers.map(j => i+":"+j))
    println(mapCombinations)

    /*
    listIntegers.foreach(x => println(x))
    listIntegers.foreach(println)                       //Mesma coisa

    val otherListIntegers:MyList[Int] = new Node(13, new Node(4, new Node(-2,Empty)))
    println(otherListIntegers.sort( (x,y) => x-y) )
    println(otherListIntegers.sort( (x,y) => y-x) )

    val listOfString:MyList[String] = new Node("Doido",new Node("Recursão",new Node("Louquíssima",Empty)))
    println(listIntegers.zipWith[String,String](listOfString, _ + "-" + _))
    println(otherListIntegers.fold(0)(_ + _))
    println(otherListIntegers.fold(0)((x:Int,y:Int) => x+y))                //mesma coisa
    */


    /*
     val cloneListIntegers:MyList[Int] = new Node(1, new Node(2, new Node(3,Empty)))

     println(listIntegers == cloneListIntegers)                  //true

     val node_01_case = Node(3,Empty)
     val node_02_case = node_01_case.add(2)
     val node_03_case = node_02_case.add(1)
     println(listIntegers + " e " + node_03_case)
     println(listIntegers == node_03_case)                       //true tbm
 */


    /*
   //  EXEMPLOS DA VERSÃO V3
        //Instanciando uma trait pelo annonymous class
//        println(listIntegers.map(new ((Int) => Int) {
//            override def apply(element: Int): Int = math.pow(element,2).toInt
//        }))
        val mapper: (Int => Int) = x => math.pow(x,2).toInt
        println(listIntegers.map(math.pow(_,2).toInt))
        println(listIntegers.map(mapper))

//        println(listIntegers.filter(new ((Int) => Boolean) {
//            override def apply(elem: Int): Boolean = elem % 2 == 0
//        }))

        println(listIntegers.filter(_ % 2 == 0))

        val anotherListIntegers:MyList[Int] = new Node(7, new Node(8, new Node(9,Empty)))
        println(listIntegers ++ anotherListIntegers)

//        println(anotherListIntegers.flatMap(new ((Int) => MyList[Int]) {
//            override def apply(element: Int): MyList[Int] = new Node(element,new Node(element + 1,Empty))
//        }))

        println(anotherListIntegers.flatMap(x => new Node(x,new Node(x + 1,Empty))))
*/



    /* Exemplos da versão v2(v1 n tem mesmo, o código era como em testzins/MyList_2)

    val node_1 = new Node(3,Empty)
    val node_2 = new Node(2,node_1)
    val node_3:MyList[Int] = new Node(1,node_2)

//    val node_3:MyList[Int] = new Node(1, new Node(2, new Node(3,Empty)))    // mesma coisa

    println(node_3.toString)                            //Funciona como uma pilha
    println(node_3.getClass)

    val node_1_str = new Node("Lista",Empty)
    val node_2_str = new Node("A",node_1_str)
    val node_3_str = new Node("Olha",node_2_str)
    println(node_3_str.toString)

    val node_01_str = new Node("Lista",Empty)
    val node_02_str = node_01_str.add("Outra")
    val node_03_str = node_02_str.add("Olha")
    println("Não precisa do .toString " + node_03_str)

    val node_1_animal = new Node(new Animal,Empty)
    val node_2_animal = new Node(new Cat,node_1_animal)
    val node_3_animal = new Node(new Dog,node_2_animal)
    val node_4_animal = new Node(34,node_3_animal)
    val node_5_animal = new Node("Olha a string",node_4_animal)
    println(node_5_animal.toString)

    val node_6_animal = new Node(new Cat,Empty)
    println("node 6 = " + node_6_animal.getClass)
    val node_7_animal = node_6_animal.add(new Animal)
    println("node 7 = " + node_7_animal.head.getClass)


     */

    val multiLine =
        """
          |Hello,iaew
          |meu
          |xapa
          |""".stripMargin
    println(multiLine)
}

