package lectures.part3fp

import scala.annotation.tailrec

object TuplesAndMap extends App {
    //Tuple = finite ordered "lists" com tipos heterogeneos
    //Technically, Scala 2.x tuples aren’t collections classes, they’re just a
    // convenient little container. Because they aren’t a collection, they don’t
    // have methods like map, filter, etc.

    val atuple = new Tuple2(2, "Stringzona")
    // com companion object
    val atuple1 = Tuple2(2, "Stringzona")
    // com syntatic sugar
    val atuple2 = (2, "Stringzona") //Tuple2[Int,String] = (2,"Stringzona")
    val atuple3 = 2 -> "Stringzona"
    println(atuple3)
    val (tp3,tp4) = atuple3
    //No máximo 22 elementos de tipos diferentes ou não

    println(atuple2._1) //index do primeiro elemento(a key)
    println(atuple2.copy(_2 = "Stringzinnha")) //Substitui o segundo índice(o value)
    println(atuple2.swap) //Só pra tuple2

    //Maps - collections de key -> value que são imutable
    val aMap: Map[String, Int] = Map()

    val phoneBook = Map(("Jim", 555), ("Daniel", 789))
    //ou
    val phoneBook1 = Map("Jim" -> 555, "Daniel" -> 789)
    //     val phoneBook1 = Map("Jim" -> 555, "Daniel" -> 789).withDefaultvalue(-1)
    println(phoneBook)
    println(phoneBook1)

    //Ops

    //keys
    //Recebe uma key e retorna um Boolean(se ela existe ou não)
    println(phoneBook.contains("Jim"))
    //.apply("key")
    println(phoneBook("Jim"))
    //Pra retornar um valor default, em vez de uma exceção, quando chamar uma key que não existe
    val phoneBook2 = phoneBook.withDefaultValue(-1)
    println(phoneBook2("Dougras"))

    //Adding a pairing
    // pra remover é só trocar por -
    val newTupla = "Maria" -> 666
    val phoneBook3 = phoneBook2 + newTupla
    println(phoneBook3)
    val phoneBook9 = phoneBook2 + ("Tucotuco" -> phoneBook2("Tucotuco"))
    println("AAAA" + phoneBook9)

    //Updating
    val newRose = phoneBook9.updated("Jim",666)
    println(newRose)
    //Functionals
    //Temos map,flatMap e filter
    //Eles recebem um pairing(tupla)
    println(phoneBook3.map(pair => (pair._1.toLowerCase -> pair._2.toFloat)))
    //obs: => é de função, -> é da tupla, <- é do for

    //mapValues
    //Faz um map pros values
    println(phoneBook3.mapValues(_ * 3))
    println(phoneBook3.mapValues("+55 " + _))

    //filterKeys
    //Só retorna os maps onde as keys retornam true pro predicado
    println(phoneBook3.filterKeys(_.length > 3))

    //Conversions to other collections
    println(phoneBook3.toList)
    println(List(("Tito", 171)).toMap)

    val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
    println(names.groupBy(name => name.charAt(0)))
    //Vai agrupar os nomes que tiverem o mesmo primeiro caractere
    println(names.groupBy(_.length))
    //Vai agrupar os nomes que tiverem o mesmo tamanho

    //Exercícios

    val mapper = Map("Dougras" -> 123, "jim" -> 546, "JIM" -> 5664)
    println(mapper.map(pair => (pair._1.toLowerCase -> pair._2)))

    //Esse map vai resultar em chaves repetidas, que na vdd vai pegar só a primeira

    case class SocialNetwork(members: Map[String, Set[String]]) {
        def addPerson(person: String): SocialNetwork = {
            if (!members.contains(person)) SocialNetwork(members + (person -> members(person)))
            else throw new RuntimeException("Pessoa já existente")
        }

        /*
        Não funciona pra essa implementação, mas pra uma que fica passando a network funciona
         */

        //        def removePerson(person: String): SocialNetwork = {
        //            // Removendo dos amigos
        //            if (members.contains(person)) {
        //                @tailrec
        //                def removeAux(friends: Set[String], networkAcc: Map[String,Set[String]]) : Map[String,Set[String]] = {
        //                    if (friends.isEmpty) networkAcc
        //                    else removeAux(friends.tail,unfriend(person,friends.head).members)
        //                }
        //
        //                val unfriended = removeAux(members(person),members)
        //                SocialNetwork(unfriended - person)      // Por fim, removendo a key
        //
        //            }
        //            else throw new RuntimeException("Pessoa não existente")
        //        }

        def friend(person1: String, person2: String): SocialNetwork = {
            if (members.contains(person1) && members.contains(person2)) {
                val friends1 = members(person1)
                val friends2 = members(person2)

                //Esses (key,value) substituem os anteriores
                SocialNetwork(members + (person1 -> (friends1 + person2)) + (person2 -> (friends2 + person1)))

            } else throw new RuntimeException("Pessoa não existente")
        }

        def unfriend(person1: String, person2: String): SocialNetwork = {
            if (members.contains(person1) && members.contains(person2)) {
                val friends1 = members(person1)
                val friends2 = members(person2)

                SocialNetwork(members + (person1 -> (friends1 - person2)) + (person2 -> (friends2 - person1)))

            } else throw new RuntimeException("Pessoa não existente")
        }

        def numberFriends(person: String) = {
            if (members.contains(person)) members(person).size
            else throw new RuntimeException("Pessoa não existente")
        }

        //_._2 é pra funções que recebem predicados que vc passa o pair
        //_ é o pair e ._2 é o segundo elemento(value)
        def personMostFriends() = {
            //maxBy recebe função com pair => algo com order
            // ou members.maxBy(pair => pair._2.size)._1
            members.maxBy(_._2.size)._1
        }

        def noFriends() = {
            //ou members.filter(key => members(key)).size
            //ou members.filter(_._2.isEmpty).size
            members.count(_._2.isEmpty)
        }

        def conection(person1: String, person2: String): Boolean = {

            //Consigo encontrar target em discoveredPeople tendo já considerado consideredPeople?
            @tailrec
            def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
                if (discoveredPeople.isEmpty) false
                else {
                    val person = discoveredPeople.head

                    if (person == target)
                        true
                    else if (consideredPeople.contains(person)) //Ou seja, os vizinhos já foram percorridos
                        bfs(target, consideredPeople, discoveredPeople.tail)
                    else //Pq já considerei ela ali no if
                        bfs(target, consideredPeople + person, discoveredPeople.tail ++ members(person))
                }
            }

            bfs(person2, Set(), members(person1))

        }

    }


    val members: Map[String, Set[String]] = Map().withDefaultValue(Set())
    var twitter = SocialNetwork(members)
    twitter = twitter.addPerson("Tito")
    twitter = twitter.addPerson("Douglas")
    twitter = twitter.addPerson("Felipe")
    twitter = twitter.addPerson("Naélio")
    twitter = twitter.friend("Tito", "Naélio")
    twitter = twitter.friend("Douglas", "Felipe")
    twitter = twitter.friend("Tito", "Felipe")
    twitter = twitter.addPerson("Zé Neto")
    println(twitter)
    twitter = twitter.friend("Zé Neto", "Douglas")
    println(twitter)
    println(twitter.conection("Zé Neto","Naélio"))

}