package lectures.part3fp

trait MyFunction[A,B]{
    def apply(element: A): B
}

object WhatsAFunction extends App{
    // Objetivo: Usar funções como first class elements, ou seja, usar funções como usamos valores puros
    
    val tripler = new MyFunction[Int,Double] {
          override def apply(element: Int): Double = math.pow(element.toDouble,3)
    }

    println(tripler(3))

    // Scala provides Function types(traits já prontos) = Functionx[arg type,arg type,...,return type]
    val strToInt = new Function1[String,Int] {
        override def apply(v1: String): Int = v1.toInt
    }

    val adder_1 = new Function2[Int,Int,Int] {
        override def apply(v1: Int, v2: Int): Int = v1 + v2
    }

    //Mesma coisa
    val adder_2 = new ((Int, Int) => Int) {
        override def apply(v1: Int, v2: Int): Int = v1 + v2
    }

    println(strToInt("38"))
    println(adder_2(34,15))

    //Function types Function2[A,B,R] podem ser escritos como (A,B) => R

    //ALL SCALA FUNCTIONS ARE OBJECTS
    // Ou instância de classes de FunctionX

    /* EXERCÍCIOS */

    val concatenator = new ((String,String) => String) {
        override def apply(v1: String, v2: String): String = v1 + v2
    }

    println(concatenator("Sua","Mão"))

    val specialFunction: Function1[Int, Function1[Int,Int]] = new Function1[Int, Function1[Int,Int]] {
        override def apply(v1: Int): Function1[Int,Int] = new Function1[Int,Int]{
            override def apply(v2: Int): Int = v1 + v2
        }
    }

    //With Syntatic Sugar
    val specialFunction_1: ((Int) => ((Int) => Int)) = new ((Int) => ((Int) => Int)) {
        override def apply(v1: Int): ((Int) => Int) = new ((Int) => Int){
            override def apply(v2: Int): Int = v1 + v2
        }
    }

    //specialFunction retorna uma Function1
    val adder = specialFunction(3)       //v1
    println(adder(4))                    //v2
    println(specialFunction(3)(4))       //curried function é uma função que recebe multiple parameters list
    println(specialFunction_1(3)(4))
    println(concatenator.getClass)
}
