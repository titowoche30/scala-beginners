package lectures.part3fp

import scala.util.Random

object Options extends App{
    //Option é uma ED, que é um wrapper para um valor que pode ou não estar presente
    //O objetivo é não ter nullPointerExceptions
    // A intenção é nunca ter que checar por nulls, o Option deve fazer isso por nós


    //2 classes herdam dela
        //Some: Wraps a concrete value
        //None: A singleton for absent values, significa a ausência de um valor

    //ex:
    val mapinha = Map(("Tito" -> 7),("Claudemir" -> 77))
    println(mapinha.get("Tito"))            //Retorna Some(7)
    println(mapinha.get("Woche"))           //Retorna None
//    println(mapinha("Woche"))               //Joga uma exception

    val someOption: Option[Int] = Some(23)
    val noneOption: Option[Int] = None

    //Pra lidar com unsafe APIs

    //método que devia retornar String mais por algum motivo retorna null
    def unsafeMethod(): String = null

    //val result = Some(unsafeMethod()) //ERRADO, pq pode vir null, e Some(Null) n faz sentido
    val result = Option(unsafeMethod())  ///CERTO. O apply do Option vai colocar pra None ou Some de acordo
    println(result)

    //chained methods
    def backupMethod(): String = "Resultado top!"
    //Se unsafe retornar None, executa o do OrElse
    val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

    //Better Design unsafe APIs
    //Já retorna tudo como Option
    def betterUnsafeMethod(): Option[String] = Option(null)       //None
    def betterBackupMethod(): Option[String] = Some("Resultado topeiro!")

    val chainedResult1 = betterUnsafeMethod() orElse betterBackupMethod()
    println(chainedResult1)
    //Funções de Options
    println(someOption.isEmpty)             //False
    println(noneOption.isEmpty)             //True
    println(chainedResult1.get)             //NÃO USE. Pq pode ter um Null, aí quebra tudo, inclusive a ideia de Option

    // map, flatmap, filter
    // Quando usa esses, ele itera nos tipos de dentro do Option, ou seja, os tipos normais
    // É um jeito de acessar eles sem dar get
    println(someOption.map(_ * 2))
    println(someOption.filter(_ % 2 == 0))      //Retorna None caso não pegue ninguém
    println(someOption.flatMap(x => Option(x*10)))

    // for-comprehensions

     val config = Map(
         "host" -> "176.45.23.1",
         "port" -> "80"
     )

    class Connection{
        def connect = "Connected"
    }
    object Connection{
        val random = new Random(System.nanoTime())

        //Safe API que retorna uma Option[Connection] dado um host e uma porta
        def apply(host:String,port:String): Option[Connection] =
            if (random.nextBoolean()) Some(new Connection())
            else None
    }

    //Problema: o config tem values que foram fetched de outro lugar, ou seja, não tenho a ctz
    // que as keys tem values associados, eles podem ou não estar lá,

    // Minha solução ruim colocando host:Option[String],port:Option[String]
    /*
    val cone = Connection
    println(cone(config.get("host"),config.get("port")))

    val compre = for{
         i <- 1 to 100
    }yield{
        val result = cone(config.get("host"),config.get("port"))
        if (!result.isEmpty) result.get.connect
        else None
    }
    println(compre)
    */

    val host = config.get("host")       //São some
    val port = config.get("port")

    val forada = for{
        i <- 1 to 10
    }yield {
        //connection é um Option(Connection)
        val connection = host.flatMap(h => port.flatMap(p => Connection(h,p)))
        val connectionsStatus = connection.map(c => c.connect)
        connectionsStatus orElse (Some("Num foi"))
    }
    println(forada)

    /* Traduzindo host.flatMap(h => port.flatMap(p => Connection(h,p))) pra imperativo
        if (h != null)
            if (p != null)
                return Connection(h,p)
        return null
     */

    //Versão mais funcional (nao printa os None)
    //chainned
    config.get("host")
      .flatMap(h => config.get("port")
      .flatMap(p => Connection(h,p))
        .map(connection => connection.connect))
    .foreach(println)

    //Versão com for-comprehension

    //Se host,port ou connection forem None, o for retorna None
    val forConnection = for{
        host <- config.get("host")
        port <- config.get("port")
        connection <- Connection(host,port)
    }yield connection.connect

    forConnection.foreach(println)



}
