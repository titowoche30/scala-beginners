package lectures.part4pm

object PatternsEverywhere extends App{
    //O catch do try-catch na vdd é um match
    //É como se na vdd fosse assim
//    try{
//        //code
//    }
//    catch (e){
//        e match {
//            case e: RuntimeException => "runtime"
//            case npe: NullPointerException => "npe"
//        }
//    }


    //ALl generators are also based on pattern matching

    //Multiple value definitions based on name binding do pattern matching
    val tuple = (1,2,3)
    val (a,b,c) = tuple

    val head :: tail = List(1,2,3,4,5)
    println(head)
    println(tail)

    val A = List(1,2,3,4,5) match {
    //    case List(head,_*)   => head
        case lista @ List(_*) => lista.tail
    }
    println(A)

    //partial function
    val otaLista = List(10,12,12,13,14,417)
    //Ele faz um match pra cada elemento
    val mappedLista = otaLista.map({
        case v if v % 2 == 0 => "Par"
        case 417 => "O maior"
        case _ => "Outro"
        } //partial function literal
    )
    println(mappedLista)

    //É O MESMO DE
    val mappedLista1 = otaLista.map(x => x match {
        case v if v % 2 == 0 => "Par"
        case 417 => "O maior"
        case _ => "Outro"
    } //partial function literal
    )
    println(mappedLista1)


}

