package lectures.part2oop

object Objects extends App{
    // Class-level funcionality = conceito de usar uma funcionalidade duma classe
    // sem depender duma instância dela
    // no Java seria:
    /*
    class Person{
        public static final int n_eyes = 2;
    }
     */

    //SCALA NÃO TEM CLASS-LEVEL FUNCIONALITY (sem static), mas tem o objects.
    //O equivalente desse código Java pro scala seria

    //Quase igual a classes, mas eles NÃO recebem parâmetros
//    object Person{
//        val n_eyes = 2
//        def canFly: Boolean = false
//    }
//
//    //Consigo acessar como se fosse um tipo(igual classe)
//    //obs: em trait não pode acessar como se fosse um tipo
//    println(Person.n_eyes)
//    println(Person.canFly)
//
//    //Scala object = singleton instance, ou seja, só tem uma instância
//    val mary = Person
//    val john = Person
//    println(mary == john)   //true, pq apontam pra mesma instâncias

    //COMPANIONS PATTERN
    //Posso escrever uma class com o mesmo nome dum object.
    //Isso é pra separar as funcionalidades de instância de classe pras funcionalidades de tipo/objeto
    //Person são companions pq estão no mesmo escopo e têm o mesmo nome

    //Singleton instance
    object Person{
        // "class" - level funcionality
        val n_eyes = 2
        def canFly: Boolean = false

        //factory method = Constrói outro objeto apartir de parâmetros
        def apply(mother: String, father: String): Person = new Person("Bobbie")
    }

    //Regular instance
    class Person(name: String = ""){
        //instance-level funcionality
        def canFly: Boolean = true
    }

    val person_class = new Person
    println(person_class.canFly)       //true
    println(Person.canFly)             //false

    //É o apply do object Person que me retorna uma instância da classe Person
    val tito = Person("pablo","taty")
    val array = Array("assim","é","melhor")   //é exatamente isso
    println(array(1))

    //Scalla Application é um Scala object com
    //def main(args: Array[String]): Unit
    //pq SA são transformados em JVM applications que o entrypoint tem que ser o psvm do Java
    //como o object singleton é o esquema de static do scala, funfa assim
}
