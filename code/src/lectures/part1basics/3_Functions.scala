package lectures.part1basics

import scala.annotation.tailrec

object Functions extends App{
    // O : tururu de retorno não é obrigatório, o compilador consegue inferir, mas é obrigatório pra funções
    // recursivas
    def aFunction(a: String, b: Int): String = {
        a + " " + b
    }

    println(aFunction("Hello", 1234))

    def aParameterlessFunction(): Int = 34

    //Posso chamar a função com ou sem parênteses
    println(aParameterlessFunction())
    println(aParameterlessFunction)

    def multiplyStrings(aString: String, n: Int): String = {
        if (n==1) aString
        else aString + multiplyStrings(aString,n-1)
    }

    println(multiplyStrings("Tito",4))

    //Quando precisar de loops, use recursão

    //Posso declarar função dentro de code block de função

    def aBigFunction(n: Int):Int = {
        def aSmallerFunction(a: Int, b: Int): Int = a + b

        aSmallerFunction(n,n-1)
    }

    println(aBigFunction(13))

    /* EXERCÍCIOS */

    def greetingFunction(name: String, age: Int): String = {
        "Hi, my name is " + name + " and I am " + age + " years old"
    }
    println(greetingFunction("Tito",22))

    def fact(n: Int): Int = {
        if (n==1) 1
        else n*fact(n-1)
    }
    println(fact(5))

    def fib(n: Int): Int = {
        if (n<=2) 1
        else fib(n-1) + fib(n-2)
    }
    //1,1,2,3,5,8,13,21,34
    println(fib(9))

    def isPrime(n: Int): Boolean = {
        //println("olha o n =" + n)
        @tailrec
        def isPrimeUntil(i: Int): Boolean = {
            //println("olha o i =" + i)
            // Testa se n é primo até i( i < n), ou seja, n tem algum divisor até i
            if (i <= 1) true //Se chegou até aqui e não dividiu ninguém, então é primo
            else n % i != 0 && isPrimeUntil(i - 1) //Se o primeiro for true, então não é divisível, logo, preciso continuar
            // iterando até achar ou não um divisível. Vendo assim: Esse não dividiu, então vou pro próximo. Até uma hora chegar
            // no 1 e ficarmos com True && True que retorna True, ou seja, é primo, ou até a hora que dividir, o primeiro termo
            // dar false e já retorna false sem iterar nos outros.
        }
        isPrimeUntil(n / 2)
    }



    println(isPrime(15 * 345))
    println(isPrime(97))



}