package lectures.part1basics

object CBNvsCBV extends App{
    /*

    * Call by value
        * A chamada padrão das linguagens
        * É passado um valor
        * Esse valor é computado antes da chamada da função
        * Esse mesmo valor é usado na função inteira
    * Call by name
        * É passada uma expression
        * Essa expressão é calculada toda vez que é usada dentro da função

     */

    def calledByValue(x: Long): Unit = {
        println("By value: " + x)
        println("By value: " + x)
    }

    def calledByName(x: => Long): Unit = {
        println("By name: " + x)
        println("By name: " + x)

        /*
            * A seta => diz para o compilador que o parâmetro vai ser chamado by name
         */
    }

    calledByValue(System.nanoTime())
    calledByName(System.nanoTime())

    /*
    Resultado:
        By value: 3267799206021
        By value: 3267799206021
        By name: 3267916780606
        By name: 3267916828320

    * Ou seja, na by value, a expressão foi computada antes da chamada, o valor resultante passado para a
    * função e ele mesmo usado durante toda a função.
    * Na by name, a expressão só foi computada quando chamada dentro da função.

    O que acontece é isso:

        def calledByValue(x: Long): Unit = {
            println("By value: " + 3267799206021L)
            println("By value: " + 3267799206021L)
        }
        calledByValue(3267799206021L)

        def calledByName(x: => Long): Unit = {
            println("By name: " + System.nanoTime())
            println("By name: " + System.nanoTime())
        }

        calledByName(System.nanoTime())

     */

    def infinite(): Int = 1 + infinite()
    def printFirst(x: Int, y: => Int): Unit = println(x)

    //printFirst(infinite(),34)           //Dá erro pq o infinite foi passado por valor, ou seja, a expression vai ser calculada e depois passada.
    //printFirst(34,infinite())           //Não dá erro pq o infinite foi passado por name, ou seja, só vai ser calculado quando a função chamar ele,
                                        // como a função não chama, ele não é calculado.
}
