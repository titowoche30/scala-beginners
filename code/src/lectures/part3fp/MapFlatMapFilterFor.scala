package lectures.part3fp

object MapFlatMapFilterFor extends App{
    val list = List(1,2,3,4)
    println(list.head)
    println(list.tail)

    //map
    println(list.map(_ * 2))
    println(list.map(x => x*2))
    println(list.map(_ + "-concata"))

    //filter
    println(list.filter(_ % 2 == 0))

    //flatMap
    //flatMap() method is identical to the map() method, but the only difference is
    //that in flatMap the inner grouping of an item is removed and a sequence is generated.
    //It can be defined as a blend of map method and flatten method.

    //foreach
    list.foreach(println)

    val toPair = (x:Int) => List(x,x+1)
    println(list.flatMap(toPair))

    //print all combinations between two lists
    val numbers = List(10,11,12,13)

    val chars = List('a','b','c','d')

    /* Tentei e falhei
    val toFlat = ((x:Int,c:Char) => List(x+c))
    val toFlatCurrie = (x:Int) => ((c:Char) => x + "" + c)

    val toFlatCurrie3 = toFlatCurrie(3)
    println(chars.map(toFlatCurrie3))
  //  println(chars.map(numbers.map(toFlatCurrie)))
*/

    //2 nested loops
    val combinations = numbers.flatMap(x => chars.map(c => x+""+c))
    println(combinations)

    //3 nested loops
    val colors = List("Black","Yellow")
    val combinations2 = numbers.flatMap(x => chars.flatMap(c => colors.map(d => x+""+d+c)))
    println(combinations2)

    //for clássico
    for(i <- 0 to numbers.length-1) println(numbers(i))


    for {
        n <- numbers
    }println(n)

    //for-comprehensions (3 forzão de imperativo)
    val forCombinations = for{
        n <- numbers
        c <- colors
        char <- chars
    } yield n+""+c+char

    println(forCombinations)

    val forCombinationsFiltered = for {
        n <- numbers if n % 2==0
        c <- colors
        char <- chars
    }yield n+""+c+char

    println(forCombinationsFiltered)

    //For comprehensions na vdd são flatmap,filter e maps reescritos pelo compilador
    //pra conseguir usar, a ED tem que ter implementações desses seguindo essa forma:
        //map(f: A=> B) => ED[B]
        //filter(p: A => Boolean) => ED[A]
        //flatMap(f: A => ED[B]) => ED[B]

    // Syntax overload
    list.map { x=>
        x * 2
    }

    val nums = (1 to 10).toList
    println(nums.foldLeft(0)(_ + _))
    println(nums.reduce(_ + _))
    def meuReduce(list:List[Int]):Int = {
        def reducer(curList:List[Int],acum:Int):Int={
            if (curList.isEmpty) acum
            else reducer(curList.tail,curList.head + acum)
        }
        reducer(list,0)

    }
    println(meuReduce(nums))

}
