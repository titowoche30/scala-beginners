package lectures.part1basics

object Expressions extends App{
    val x = 1 + 2 // O RHS é chamado de EXPRESSION
    println(x)

    println(x == 34)
    // ==
    // !=
    // > ; >=
    // < ; <=

    println(!(x==34))
    // !
    // &&
    // ||

    var aVariable = 99
    aVariable+=23
    println(aVariable)
    //+= ; -= ; *= ; /=    Eles são side effects(pq alteram variáveis)

    //Instructions(DO) VS Expressions(VALUE)

    //Instructions(executed) = Algo que vc manda o PC fazer. Imperative Languages
    //Expressions(evaluated) = Algo com valor e/ou tipo. Programação funcional se baseia nisso,
    // toda parte do código vai computar um valor

    //if expression
      //if no scala é uma expression, ou seja, retorna um valor

    val aCondition = true
    val aConditionedValue = if(aCondition) 5 else 3
    println(aConditionedValue)
    println(if(aCondition) 5 else 3)
    //Em outras linguagens, se a condição fosse true, seria executada uma instrução(nesse caso setar pra 5)
    //e se false outra instrução
    //No scala como o if é uma EXPRESSION(não uma INSTRUCTION), ele já retorna um valor

    //LOOPS existem mas são desencorajados por serem coisa de programação imperativa, eles não retornam nada, só
    // executam side effects.
    // TUDO EM SCALA SÃO EXPRESSIONS(menos umas coisinhas, como definições)

    val aWeirdValue = (aVariable = 3)     //aWeirdValue vai receber Unit que é o void do Scala
    println(aWeirdValue)

    //Side effects são expressions que retornam Unit
    // Um while, que é um side effect, tbm retorna Unit
    // Mais side effects: println(), whiles, reassigning


    //CODE BLOCKS
      //Tipos especiais de expressions

    //Dentro de um podemos escrever código em geral. As val definidas dentro dele, só existem dentro dele.
    //Como é uma expression, vai retornar um valor, esse valor é o valor da última expression dentro do code block.

    val acodeBlock = {
        val y = 2
        val z = y + 1

        if (z>2) "hello" else "goodbye"
    }

    //Pode ser var tbm(apesar de não fazer mto sentido).
    //Depois que o compilador infere um tipo pra var, ele só pode ser mudado explicitamente
    //com um cast.

    println(acodeBlock)


}
