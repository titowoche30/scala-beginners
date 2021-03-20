package lectures.part4pm

import exercises.MyList
import exercises.Node
import exercises.Empty

object AllThePatterns extends App{
    // 1 - Constants
    //Funciona com literais e singleton Objects
    val x: Any = "Scala"
    //Funciona pq é Any

    val constants = x match {
        case 1 => "Olha o 1"
        case _ => "Se acalme"
    }

    // 2 - match anything
    //2.1 wildcard

    val matchAnything = x match{
        case _ => "Tururu"
    }
    println(matchAnything)

    //2.2 variable
    val matchVariable = x match {
        case n => s"Chegou $n"
    }
    println(matchVariable)

    //3 - Tuples
    val aTuple = (14,2)
    val matchTuple = aTuple match{
        case (1, 1) => "1 e 1 hein"
        case (n,2) => s"veio o $n junto hein"
        case _ => "Relaxe"
    }
    println(matchTuple)

    val nestedTuple = (1,(10,20))
    val nestedMatch = nestedTuple match {
        case (_,(10,v)) => v*2                          //Consigo extrair da tupla interna
    }
    println(nestedMatch)

    //4 - case classes - constructor pattern
    val alist: MyList[Int] = Node(1,Node(2,Empty))
    val matchedList = alist match {
        case Empty => s"Cabou hein"
    //    case Node(h,t) =>
        case Node(h,Node(subh, subt)) =>
    }

    val mapinha = Map(
        "I" -> 1,
        "V" -> 5,
        "X" -> 10
    )

    mapinha.foreach {
        case (romano,ocidental) => println(s"O romano $romano vale $ocidental")
    }


    //5 - list patterns
    val aList = List(1,2,3,42)
    val matchList = aList match {
        case List(1,_,_,_) => "Tem 4 elementos com 1 no primeiro hein"         //extractor. Mesmo List não sendo uma case class, o constructor extractor de List existe
        case List(2, _*) => "Tem x elementos e 2 no primeiro hein"             //list of arbitrary length
        case 1 :: List(_*) => "Começa com 1 e tem x elementos hein"            //infix pattern
        case List(1,2,3) :+ 42 => "É 1,2,3,42 hein"                            //infix pattern
    }
    println(matchList)

    //6 - type specifiers
    val unknown: Any = Node(1,Node(2,Empty))
    val unknownMatch = unknown match {
        case list:List[Int] => "É lista de int hein"
        case list:MyList[Int] => "É minha lista de int hein"
        case _ => "Num sei hein"
    }
    println(unknownMatch)

    //7 - name binding
    val nameBindingMatch = alist match {
        case nomeProObjeto @ Node(_,_) => nomeProObjeto.toString       //Assim eu nomeio o objeto inteiro
        case Node(1,nomeSubObjeto @ Node(2, _)) => nomeSubObjeto.toString
    }
    println(nameBindingMatch)

    //8 - multi-patterns
    //multiple patterns chained by a pipe(|) operator
    val multiPattern = alist match {
        case Empty | Node(0,_)  => "É um ou outro"   //compound pattern
        case _ => "nada"
    }

    //9 - if guards
    val secondElementSpecial = alist match {
        case Node(_,Node(specialElement,_)) if specialElement%2==0 => "É par hein"
        case _ => "nada"
    }

    //QUESTÃO IMPORTANTE

    val numbers = List(1,2,3)
    val numbersMatch = numbers match {
        case listStrings: List[String] => "Strings hein"
        case listInts: List[Int] => "Ints hein"
    }
    println(numbersMatch)
    //Vai dar strings pq a JVM faz um negócio chamado Type Erasure, ou seja, ignora os tipos
    //Ou seja, na prática fica assim
//    val numbersMatch = numbers match {
//        case listStrings: List[String] => "Strings hein"
//        case listInts: List[Int] => "Ints hein"
//    }



}


