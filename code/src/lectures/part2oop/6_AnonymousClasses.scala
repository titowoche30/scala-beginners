package lectures.part2oop

object AnonymousClasses extends App{
    //Poderia ser trait tbm
    abstract class Animal {
        def eat: Unit
    }

    //Anonymous Class
    val funnyAnimal: Animal = new Animal {
        override def eat: Unit = println("hehehe")
    }
    println(funnyAnimal.getClass)

    //Ou seja, posso instanciar uma abstrata, desde que override tudo no spot

    /*

    ISSO É EQUIVALENTE A


        class AnonymousClasses$$anon$1 extends Animal {
            override def eat: Unit = println("hehehe")
        }
        val funnyAnimal: Animal = new AnonymousClasses$$anon$1


    */

    //Também funciona pra classe não abstrata.

    class Person(name: String) {
        def sayHi: Unit = println(s"Hi, my name is ${this.name}, how can I help?")
    }

    val jim = new Person("Jim") {
        override def sayHi: Unit = println(s"Hi, my name is Jim, how can I be of service?")
        // não consigo acessar os parâmetros com this
    }

    jim.sayHi

    //A intenção é instanciar e overridar no spot.

}
