package lectures.part2oop

//class Person(name: String, age: Int) // construtor
//parâmetros da classe NÃO são fields, ou seja, se eu desse um person.age, ele não acessaria

//pra transformar parâmetros em fields, é só colocar um val antes
// Basicamente sem val(var) é privado e com é público


/*
class Person(val name: String, val age: Int)


object OOBasics extends App{
    val person = new Person("Tito",22)
    println(person.name + " " + person.age)
}
*/

/*
class Person(val name: String, val age: Int) {
    // body
    val numOfLegs = 2                   //field

    println("Instânciou hein !")        //vai ser executado SEMPRE que essa classe for instanciada



}

object OOBasics extends App{
    val person = new Person("Tito",22)
    println(person.name + " " + person.age)
    println("legs:"+ person.numOfLegs)

}
*/

/*
class Person(name: String, val age: Int) { // construtor
    // body
    val numOfLegs = 2                   //field

    println("Instânciou hein !")        //vai ser executado SEMPRE que essa classe for instanciada

    //method
    def greeting(name: String): Unit = println(s"${this.name} says hello to ${name}")
    // o this se refere a um parâmetro seja ele field ou não
    // Se não existisse outro name na função, a chamada de name sem o this já se referiria ao do construtor

    //overloading - métodos com mesmos nomes mas signatures(tipos e numero de parâmetros) diferentes
    def greeting(): Unit = println(s"Hello, my name is ${this.name}")


}

object OOBasics extends App{
    val person = new Person("Tito",22)
    println(person.age)
    println("legs:"+ person.numOfLegs)
    person.greeting("Dougras")
    person.greeting()
}

*/


/* Exercícios */

class Writer(val firstName: String, val surName: String, val year: Int){
    def fullName(): String = s"${surName},${firstName}"
}

class Novel(val name: String, val yearRelease: Int, val author: Writer ){
    def authorAge(): Int = this.yearRelease - this.author.year

    def isWrittenBy(author: Writer): Boolean = author == this.author     //Por enquanto esse tipo de comparacao n faz sentido msm

    def copy(newYear:Int): Novel = new Novel(this.name,newYear,this.author)
}

class Counter(val acum: Int){
    // Como acum ta com val(é um field), eu posso acessar diretamente
    // Se tivesse sem, eu teria que fazer uma função pra pegar (geter)
/*
    def currentCount(): Int = this.acum
    //ou def currentCount = acum
    def increment():Counter = new Counter(this.acum + 1)            //Isso é immutability, to retornando um novo objeto(instância) em vez de modificar o atual
                                                                    //É um conceito importante de FP, sempre que precisar modificar o conteúdo dum objeto, vc deve retornar um novo
    def decrement():Counter = new Counter(this.acum - 1)

    def increment(qtd:Int): Counter = new Counter(this.acum + qtd)
    def decrement(qtd:Int): Counter = new Counter(this.acum - qtd)
*/
    // OOOU


    def inc = {
        println("Incrementando")
        new Counter(acum +1)
    }

    def dec = {
        println("Decrementando")
        new Counter(acum -1 )
    }

    def inc(n:Int): Counter = {
        if (n<=0) this
        else inc.inc(n-1)
    }

    def dec(n:Int): Counter = {
        if (n<=0) this
        else dec.dec(n-1)
    }

}

object OOBasics extends App {
    val author = new Writer("Tito","Carvalho",1997)
    val book = new Novel("A volta dos que não foram",2007,author)
    println(author.fullName())
    println(book.authorAge())
    println(book.isWrittenBy(author))
    println(book.copy(2020).yearRelease)

    val counter = new Counter(43)
    println(counter.inc.acum)
    println(counter.dec.acum)
    println(counter.inc(10).acum)
    println(counter.dec(10).acum)
}






