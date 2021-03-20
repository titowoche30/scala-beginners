package lectures.part2oop

object Exceptions extends App {
    val x: String = null
    //println(x.length)    //Vai dar um NullPointerException

    //1. Throwing exceptions

   // val aWeirdValue: String = throw new NullPointerException  // throw é expression tbm,logo retorna value, logo posso assignar

    //Throwable classes extends (herdam) Throwable class
    // Exception e Error são os Throwable subtypes principais
    // Esses dois crasham a JVM, mas existem diferenças entre eles

    //Exception = Algo que deu errado com o programa, tipo um NullPointer
    //Error = Algo que deu errado com o sistema(JVM), tipo um StackOverFlow

    //2. Catch Exceptions

    def getInt(withException: Boolean): Int = {
        if (withException) throw new RuntimeException("No int for you, little booooi!")
        else 42
    }

     try {
        getInt(true)                            //Código que pode throw uma exception
    } catch {
        case e: RuntimeException => println("DEU RUIM AQUI Ó !!!")      //Código que vai ser executado caso pegue uma exception
    } finally {
        println("Ihu")                                      //Código que vai ser executado no matter what
                                                            // é opcional e não influencia no return type da expression try/catch
                                                            //use o finally só pra side effects, tipo log pra um arquivo
    }

    //3. Definindo as próprias Exceptions

    class MyException extends Exception

    val except = new MyException
  //  throw except


    /* EXERCÍCIOS */

    def getSuperArray(size: Int):Array[Nothing]= {
        Array.ofDim(size)
    }

    try{
        val size = Int.MaxValue
        getSuperArray(size)
    } catch {
        case e: OutOfMemoryError => println("Se empolgou hein, olha o OutOfMemoryError")
    }

    def infinite(): Int = infinite() + 1

    try{
        infinite()
    }catch {
        case e: StackOverflowError => println("Oooolha o StackOverFlow menino")
    }

    case class OverFlowException() extends RuntimeException
    case class UnderFlowException() extends RuntimeException

    case object PocketCalculator {
        def add(x: Int, y: Int): Int = {
            val result = x + y

            if (x > 0 && y > 0 && result < 0)
                throw new OverFlowException
            else if (x < 0 && y < 0 && result > 0)
                throw new UnderFlowException
            else
                result
        }

        def sub(x: Int, y: Int): Int = {
            val result = x - y
            if (x > 0 && y < 0 && result < 0 )
                throw new OverFlowException
            else if(x < 0 && y > 0 && result > 0)
                throw new UnderFlowException
            else
                result
        }
        def mult(x: Int, y: Int): Int = {
            val result = x * y
            if (x > 0) {
                if (y > 0 && result < 0)
                    throw new OverFlowException
                else if ( y < 0 && result >0)
                    throw new UnderFlowException
                else
                    result
            } else if (x < 0) {
                if(y > 0 && result > 0)
                    throw new UnderFlowException
                else if (y < 0 && result < 0)
                    throw new OverFlowException
                else
                    result
            } else
                result
        }


        def div(x: Int, y: Int): Int = {
            if (y==0)
                throw new ArithmeticException
            else
                x/y
        }
    }

    val calculator = PocketCalculator

    try {
        calculator.add(Int.MaxValue -1, 2)
    }catch {
        case e: OverFlowException => println("Olha o overflow menino")
    }

    try {
        calculator.sub(Int.MinValue, 3)
    } catch {
        case e: UnderFlowException => println("Olha o underflow menino")
    }

    try{
        calculator.div(3,0)
    }catch {
        case e: ArithmeticException => println("Dividiu por 0 hein")
    }



}
