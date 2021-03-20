package lectures.part3fp

import scala.util.{Failure, Random, Success, Try}

object HandlingFailure extends App{
    //try-catch-finally blocks são difícies de ler e não dá pra chain

    //Try é uma ED Wrapper pra coisa que poderia falhar ou n
    //É pai de 2 classes
        //Failure: wrap coisas que seriam Thrown se tivesse rodado eu mesmo
        //Sucess: wrap coisas que dão certo

    val aSuccess = Success(333)
    val aFailure = Failure(new RuntimeException("CAÍ AQUI Ó"))

    println(aSuccess)
    println(aFailure)

    def unsafeMethod: String = throw new RuntimeException("TEM STRING AQUI NÃO PCO")
    //Try pelo apply do Object
    val potentialFailure = Try(unsafeMethod)
    println(potentialFailure)
    //Não quebra o programa pq o Try wrapa a exceção

    //Syntax sugar
    val anotherPotentialFailure = Try {
        //monte de código
    }

    // utilities
    println(potentialFailure.isSuccess)
    println(potentialFailure.isFailure)

    def backupMethod: String = "A valid result"
    val fallBackTry = Try(unsafeMethod) orElse Try(backupMethod)
    println(fallBackTry)

    //Design API
    def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
    def betterBackupMethod(): Try[String] = Success("Deu bom hein!")

    val betterFallBackTry = betterUnsafeMethod() orElse betterBackupMethod
    println(betterFallBackTry)

    //map,flatMap e filter
    println(aSuccess.map(_ *2))
    println(aSuccess.flatMap(x => Success(x * 10)))
    println(aSuccess.filter(_ > 1000))         //Vai transformar o Success num Failure

    //EXERCÍCIO

    def renderHTML(page: String) = println(page)

    class Connection{
        def get(url:String):String = {
            val random = new Random(System.nanoTime())
            if (true) "<html>...</html>"
            //        if (random.nextBoolean()) "<html>...</html>"
            else throw new RuntimeException("Conexão deu pau ;(")
        }

        def getSafe(url: String): Try[String] = Try(get(url))

    }

    object HttpService{
        val random = new Random(System.nanoTime())

        def getConnection(host:String,port:String): Connection = {
            if (random.nextBoolean()) new Connection
            else throw new RuntimeException("Ta trancado")
        }

        def getSafeConnection(host:String,port:String): Try[Connection] = Try(getConnection(host,port))

    }

    val hostname = "localhost"
    val port = "8080"

    //Versão padrão
    val possibleConnection = HttpService.getSafeConnection(hostname,port)
    val possibleHTML = possibleConnection.flatMap(cone => cone.getSafe("duga"))
    possibleHTML foreach renderHTML

    //Versão funcionalíssima
    val block1: Unit = {
        HttpService.getSafeConnection(hostname,port)
      .flatMap(cone => cone.getSafe("duga"))
      .foreach(renderHTML)
    }


    //Versão com for-comprehension
    val block2: Unit = {
        for {
            cone <- HttpService.getSafeConnection(hostname, port)
            html <- cone.getSafe("duga")
        } renderHTML(html)
    }

    block1
}
