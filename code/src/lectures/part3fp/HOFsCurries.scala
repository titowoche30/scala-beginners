package lectures.part3fp

import scala.annotation.tailrec

object HOFsCurries extends App{
    val superFunction: (Int, (String, (Int => Boolean)) => Int) => (Int => Int) = null
    //High Order Function (HOF)
    //Recebe uma função como parâmetro e/ou retorna uma função

    //ex: Map,Filter e flatMap do MyList

    //Função que aplica outra função n vezes em um valor x
    //nTimes(f,n,x)
    //nTimes(f,3,x) = f(f(f(x))) = nTimes(f,2,f(x)) = nTimes(f,1,f(f(x)))
    //nTimes(f,n,x) = f(...f(x)) = nTimes(f,n-1,f(x))

    @tailrec
    def nTimes(f: Int => Int, n:Int, x:Int): Int = {
        if (n <= 0) x
        else nTimes(f,n-1,f(x))
    }

    val plusOne = (x:Int) => x +1
    println(nTimes(plusOne,7,10))

    // ntb(f,n) = x => f(f...(x))
    // increment10 = ntb(plusOne,10) = x => plusOne(plusOne...(x))
    // val y = increment10(45)

    //Vai me retornar uma função que recebe um parâmetro x
    def nTimesBetter(f: Int => Int, n: Int): Int => Int = {
        if (n<=0) (x:Int) => x
        else (x:Int) => nTimesBetter(f,n-1)(f(x))           //Como nTimesBetter retorna uma function, uso o apply dela
    }

    val incrementor = nTimesBetter(plusOne,34)
    println(incrementor(11))

    //Curried Functions
    // It's a process of transforming a function,
    // transforming a function that takes multiple arguments into a function that takes single argument.

    val superAdder: Int => (Int => Int) = x => (y => x + y)
    val add3 = superAdder(3)        // 3 é o x, y => 3 + y
    println(add3(14))               //14 é o y
    println(superAdder(3)(14))      // mesma coisa

    //Funções com multiples parameter lists
    def curriedFormatter(c: String)(x: Double): String = c.format(x)

    val standardFormat: (Double => String) = curriedFormatter("%4.2f")       //To fixando o parâmetro c:String
    val preciseFormat: (Double => String) = curriedFormatter("%10.8f")
    //Agora esses 2 Formats são subfunctions de curriedFormatter

    println(standardFormat(Math.PI))
    println(preciseFormat(Math.PI))

    /*Exercícios*/

    //Transforma normal em curry
    def toCurry(f: (Int,Int) => Int): Int => (Int => Int) = {
        x => (y => f(x,y))
    }

    //Transforma curry em normal
    def fromCurry(f: Int => (Int => Int)): (Int,Int) => Int = {
        (x,y) => f(x)(y)
    }

    def compose[A,B,C](f: A => B, g: C => A): C => B = {
        x => f(g(x))
    }

    def andThen[A,B,C](f: A => B, g: B => C): A => C = {
        x => g(f(x))
    }

    //val supperAdder2: (Int => (Int => Int)) = toCurry(_ + _)        //msm coisa
    def supperAdder2: (Int => (Int => Int)) = {
        toCurry(_ + _)
    //    toCurry((x:Int,y:Int) => x + y)      //mesma coisa
    }

    //val add4 = supperAdder2(4)                                      //msm coisa
    def add4 = supperAdder2(4)
    println(add4(23))

    val quad = (x:Int) => x*x
    val sub7 = (x:Int) => x-7

    val compado = compose(quad,sub7)
    val thenado = andThen(quad,sub7)

    println(compado(5))
    println(thenado(5))


}
