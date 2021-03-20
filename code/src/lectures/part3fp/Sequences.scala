package lectures.part3fp

import scala.util.Random

object Sequences extends App {
    /*Seq é uma trait generalista pra estruturas de dados que:
        * Têm ordem bem definida
        * Podem ser indexadas
        * Suporte pra várias operações clássicas
     */

    val aSequence = Seq(1,2,3,4,5)
    println(aSequence)        //Vai ser uma List. O apply da Seq pode construir subclasses de Seq
    println(aSequence.reverse)
    println(aSequence(2))        // O apply da instância da classe é um get. aSequence[2]

    //Ranges
//    val aRange:Seq[Int] = 1 to 10         //merma coisa
    val aRange = 1 to 10
    val a2Range = 1 to 10 by 2
    aRange.foreach(x => print(x+","))
    println("")
    a2Range.foreach(x => print(x+","))
    println()
    (1 until 5).foreach(x => print(x*2))
    println()

    //Lists
    //Immutable linked list
    //head,tail e isEmpty são O(1)
    //o resto a maioria é O(n)
    //Tem dois subtypes, o Nil(Empty) e o ::(Node)

    val list = List(1,2,3)
    val listR = List.range(0,100,30)      //0 até 100 de 30 em 30 ou (0 to 100 by 30).toList
    val list2 = List(1,"ca",3)
    println(list2)
    val prepend = 42 :: list
    val prepend1 = 42 +: list
    val preappend = 42 +: list :+ 34
    println(prepend)
    println(prepend1)
    println(preappend)
    println(list(1))
    val apple5 = List.fill(5)("apple")
    println(apple5)
    println(apple5.mkString("/"))

    //Arrays
    //São os arrays do Java
    //Podem ser instânciados com o tamanho definido
    //Indexing é rápido
    //SÃO MUTÁVEIS

    val numbers = Array(1,2,3)
    val threeNumbers = Array.ofDim[Int](3)             //Aloca espaço pra 3 elementos, inicia com 0

    threeNumbers(2) = 34                                    //threeNumbers.update(2,34)
    threeNumbers.foreach(println)
    println(threeNumbers.mkString("--"))

    //Posso converter Array pra Seq
    val numberSeq: Seq[Int] = threeNumbers        //Implicit Conversion
    println(numberSeq)

    //Vector
    //Principal imutable sequence, é a principal implementation de Sequence
    //Index read e write O(log32(n))
    val otherVector = Vector(1,2,3)
    println(otherVector)
    val mixedVector = Vector(1,"cara",3.45)
    println(mixedVector)
    val noElements = Vector.empty
    val vecNumbers = noElements :+ 1 :+ 2 :+ 3
    val vecModified = vecNumbers.updated(1,34)
    println(vecModified)

    //Vectors VS Lists
    //Duelo de imutables

    val maxRuns = 1000
    val maxInd = 1000000
    def getWriteTime(collection: Seq[Int]): Double = {
        val r = new Random()
        val times = for{
            it <- 1 to maxRuns
        }yield {
            val currentTime = System.nanoTime()
            collection.updated(r.nextInt(maxInd),r.nextInt())
            System.nanoTime() - currentTime
        }
        times.sum * 1.0 / maxRuns

    }

    val numbersList = (1 to maxInd).toList
    val numbersVector = (1 to maxInd).toVector

   // println(getWriteTime(numbersList))
   // println(getWriteTime(numbersVector))

    //Vector é MUUUUITO mais rápido

    val vetor1 = (0 to 4).toVector
    val vetor2 = (5 to 9).toVector
    val vetor3 = (10 to 14).toVector
    val matriz = Vector(vetor1,vetor2,vetor3)
    println(matriz)
    println(matriz(2)(0))

}
