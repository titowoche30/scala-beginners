package lectures.part1basics
import scala.annotation.tailrec

object Recursion extends App {
//    //Essa abordagem clássica cria uma pilha de chamadas e se a pilha for muito grande, dá um stackoverflow
//    def fact(n: Int): Int = {
//        if (n <= 1) 1
//        else n*fact(n-1)
//    }
//
//    //papoca
//    //println(fact(7000))
//
//    def anotherFactorial(n: BigInt): BigInt = {
//        def factHelper(x: BigInt, accumulator: BigInt): BigInt =
//            if (x <= 1) accumulator
//            else (factHelper(x-1,x*accumulator))
//
//        factHelper(n,1)
//    }
//
//    //nao papoca
//    println(anotherFactorial(7000))

    /*
    anoterFactorial(10) = factHelper(10,1)
    = factHelper(9,10*1)
    = factHelper(8,9*10*1)
    = factHelper(7,8*9*10*1)
    ...
    = factHelper(2,3*4*5*6*7*8*9*10*1)
    = factHelper(1,2*3*4*5*6*7*8*9*10*1)
        retorna o acumulador(direita)

     */

    //Isso é recursão pela cauda. A chave aqui é que a última expression é SÓ a chamada recursiva, na primeira abordagem
    // é gerada toda uma pilha de chamadas que aos poucos vai retornando os produtos, nessa nova abordagem isso não acontece.
    // Quando está em factHelper(10,1) e chama factHelper(9,10*1) para validação, a segunda substitui a primeira, eu não
    // preciso da chamada anterior, pq a informação dela já ta no acumulador

    /* EXERCÍCIOS */

    def concatString(aString: String, n: Int): String = {
        @tailrec
        def concatStringHelper(bString: String, i: Int, accum: String): String =
            if (i == 1) accum
            else concatStringHelper(bString, i - 1, bString + accum)
        concatStringHelper(aString,n,aString)
    }

    //println(concatString("Tito ",5))

    def isPrimeTail(n: Int): Boolean = {
        @tailrec
        def isPrimeUntil(i: Int, isStillPrime: Boolean): Boolean = {
            if (!isStillPrime) false
            else if (i <= 1) true
            else isPrimeUntil(i-1, n % i != 0 && isStillPrime)
        }
        isPrimeUntil(n / 2,isStillPrime = true)
    }

//    println(isPrimeTail(15 * 345))
//    println(isPrimeTail(97))
    val n = 5849
    println(isPrimeTail(n))


    def fibTail(n: Int): Int = {
        @tailrec
        def fibHelper(i: Int, ultimo: Int, penultimo: Int): Int =
            if (i >= n ) ultimo
            else fibHelper(i+1, ultimo + penultimo, ultimo)

        fibHelper(2,1,1)
    }

    //1,1,2,3,5,8,13,21,34
//    println(fibTail(9))


}
