package lectures.part1basics

import scala.annotation.tailrec

object DefaultArgs extends App{

    @tailrec
    def tailFact(x: Int, accumulator: BigInt = 1): BigInt =
        if (x <= 1) accumulator
        else tailFact(x-1,x*accumulator)

    //Se não passar nada, vai ser o default, igual no python
    val fact10 = tailFact(10)
    println(fact10)

    //Pode passar o nome dos argumento tbm, igual no python
    val fact10_ = tailFact(x = 10)
    println(fact10)
}
