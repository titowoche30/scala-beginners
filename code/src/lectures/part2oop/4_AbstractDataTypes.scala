package lectures.part2oop

object AbstractDataTypes extends App{
    // classes abstratas = classes com métodos e/ou val(var) não implementados
    // elas não podem ser instanciadas

    abstract class Animal{
        val test: Int = 3       //pode ter coisa já implementada tbm
        val creatureType: String
        def eat(): Unit
    }

    class Dog extends Animal{
        // o override é opcional
        override val creatureType: String = "Canine"

        override def eat: Unit = println("thuco thuco")
    }

    //traits

    //traits vs abstract classes
    //1 - traits não têm construtores, logo não são instanciados
    //2 - traits tbm servem como tipos
    //3 - Só pode herdar uma abstract class, mas traits pode várias
    //4 - traits = behavior,o que eles fazem, abstract class = type of thing
    //5 - Trait é como se fosse interface do java, sendo que pode declarar coisa lá
    //6 - In fact, you only need to use an abstract class when:
        // You want to create a base class that requires constructor arguments
        // Your Scala code will be called from Java code


    trait Carnivore {
        val preferedMeal: String = "Fresh Meat"
        def eat(animal: Animal): Unit
    }

    trait ColdBlooded

    class Crocodite extends Animal with Carnivore with ColdBlooded {
        override val creatureType: String = "croc"
        override def eat(): Unit = println("tuco tuco")

        override def eat(animal: Animal): Unit = println(s"tuco ${animal.creatureType}")
    }

    val dog = new Dog
    val croc = new Crocodite
    croc.eat(dog)

}
