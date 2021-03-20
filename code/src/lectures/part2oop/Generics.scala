package lectures.part2oop

object Generics extends App {
//    //Generic Class, classes type parameterized
//    //Funciona pra trait tbm. Mas lembrando que trait não pode ser instanciada
//    //NÃO funciona pra Objects
//    //Funciona pra métodos
//
//    class MyList[A] {
//        //A é um tipo genérico
//    }
//
//    class MyMap[Key,Value]     //Key e Value são tipos
//
//    val listOfIntegers = new MyList[Int]
//    val listOfStrings = new MyList[String]
//
//    //Generic Methods
//    object MyList {
//        def empty[A]: MyList[A] = ???       //Método parametrizado por tipo
//    }
//    val emptyListOfIntegers = MyList.empty[Int]
//
//    //Variance problem
//    class Animal
//    class Cat extends Animal
//    class Dog extends Animal
//
//    // Pergunta: Se Cat extends Animal, uma lista de Cat extends lista de Animal?
//
//    // Resposta 1: Sim, List[cat] extends List[Animal] => COVARIANCE
//    // O nome desse comportamento é COVARIANCE
//
//    class CovariantList[+A]
//    val animal: Animal = new Cat
//    val animalList: CovariantList[Animal] = new CovariantList[Cat]
//
//    // Resposta 2: Não => Invariance
//    // O nome desse comportamento é INVARIANCE
//
//    class InvariantList[A]     //padrãozin, essas classes existem no seu próprio mundo, não posso
//                               // substituir uma por outra
//    //NÃO PODE
////    val invariantAnimalList: InvariantList[Animal] = new InvariantList[Cat]
//
//    // Resposta 2: Não mesmo => Contravariance
//    // O nome desse comportamento é CONTRAVARIANCE
//    class ContraVariantList[-A]
//    val contravariantList: ContraVariantList[Cat] = new ContraVariantList[Animal]
//
//    //ESSE NÃO PODE
////    val contravariantList1: ContraVariantList[Animal] = new ContraVariantList[Cat]
//
//    //bounded types
//    //Permitem o uso das generic classes só para alguns tipos que são uma subclasse
//    // de um diferente tipo ou uma superclasse de diferente tipo
//
//    //Upper bounded type
//    class Cage[A <: Animal](animal: A)     //Classe Cage só aceita types A que são subclasses de Animal
//    val cage = new Cage(new Cat)           //Lower bounded é >:

    // NÃO PODE
    //class Car
    //val car = new Cage(new Car)


     //Na Covariant, e se eu adicionasse na animalList um dog? Resolvo isso assim:

    class MyList[+A]{                   //Covariant, ou seja, pode instanciar menor no maior
        def add[B >: A](element: B): MyList[B] = ???
        //B é um supertype de A, ou seja, no final retorno B.
        //Diz o seguinte: Se em uma lista de A(cat), eu colocar um B(animal[dog é do tipo animal])
        //que é um supertype de A(cat), então essa lista vai se tornar uma lista de B(animal)
    }




}
