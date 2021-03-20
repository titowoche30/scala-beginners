package lectures.part2oop

object Inheritance extends App {
    //Single class inheritance

    //Superclass
    class Animal {
        val creatureType = "Wild"
        val temPelo = false
        def eat = println("nhac !")
//        private def eat = println("nhac !")       //Acessível só nessa classe
//        protected def eat = println("nhac !")     //Acessível só nessa classe e nas subclasses(dentro delas)
                                                    //Os métodos que são chamados com um objeto. são só os públicos, os
                                                    //protected só podem ser chamados DENTRO da subclasse
    }

    //Subclass
    class Cat extends Animal

    // Herdar uma classe = Herdar todos os fields e métodos não-privados

    val cat = new Cat
    cat.eat

    //Constructors
    class Person(name: String, age: Int)
    class Adult(name: String, age:Int, idCard: String) extends Person(name,age)
    //É assim pq a JVM primeiro chama o construtor da superclasse, depois da sub

    //overriding = Diferentes implementações em classes derivadas(subclasses)
    //overloading = Múltiplos métodos com mesmo nome e signatures diferentes numa mesma classe
    class Dog(override val temPelo: Boolean) extends Animal{
        override val creatureType: String = "domestic"
        override def eat: Unit = println("nhuc nhuc")
        //super.eat
    }

    val dog = new Dog(true)
    dog.eat
    println(cat.creatureType)
    println(dog.creatureType)

    //type substitution

    //Uma variável do tipo Animal recebendo um objeto Dog
    //Isso é polimorfismo
    //  Overriding = polimorfismo dinâmico
    //  Overloading = polimorfismo estático
    val unknownAnimal : Animal = new Dog(true)
    unknownAnimal.eat
    //Ele vai pra implementação overridadad do Dog. Uma chamada de método sempre vai pra versão mais overridada(mais baixo)

    //Previnir overriding
    //1 - colocar final def blublu (o método não vai ser overridado)
    //2 - colocar final class tururu (Previne que a classe seja herdada)
    // 3 - colocar sealed class tururu (A classe só pode ser herdada por classes do mesmo arquivo)


}
