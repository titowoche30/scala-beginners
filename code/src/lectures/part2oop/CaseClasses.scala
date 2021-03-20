package lectures.part2oop

object CaseClasses extends App {
    // Usado pra classes simples, pq já deixa disponível umas coisas que numa class normal teria que implementar

    case class Person(name: String, age: Int)

    //1. Os parâmetros viram fields
    val john = new Person("John",34)
    println(john.age)

    //2. toString implementado
    println(john)                       //Vai aparecer Person(John,34)

    //3. equals e hashcode implementados
    val john0 = new Person("John",34)
    println(john == john0)              //Vai ser True

    //4. copy implementado
    val joao = john.copy()              //John puro
    val joaozin = john.copy(age=13)     //John de age 13
    println(joaozin)

    //5. companion objects
    val pessoa = Person
    val dougras = Person("Dougras",23)          //Apply do object retorna uma nova instância da classe(factory method)
                                                //Ou seja, mesma coisa de instanciar pelo new

    //6. Case Classes são serializable, ou seja, são boas com sistemas distribuídos, vc pode mandar instâncias de case
    //classes pela rede e entre JVMs. Framework Akka lida com mensagens serializáveis pela rede e essas mensagens costu-
    // mam ser case classes

    //7. Case Classes tem extractor patterns, ou seja, podem ser usadas em pattern matching

    //8. Case Objects.
    //Mesma coisa só que não têm companion



}
