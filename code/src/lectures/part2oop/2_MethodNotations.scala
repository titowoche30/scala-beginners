package lectures.part2oop

object MethodNotations extends App{

    class Person(val name: String, favoriteMovie: String, val age: Int = 0){
        def likes(movie: String): Boolean = movie == favoriteMovie
        def hangOutWith(person: Person): String = s"${this.name} is hanging out with ${person.name}"
        def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
        def unary_!(): String = s"${this.name} !!!"
        def isAlive(): Boolean = true
        def apply(): String = s"Hi, my name is ${this.name}, I like ${this.favoriteMovie} and my age is ${this.age}"

        def +(string: String): Person = new Person(this.name + string,this.favoriteMovie)
        def unary_+(): Person = new Person(this.name,this.favoriteMovie,this.age + 1)
        def learns(string: String): String = s"${this.name} learns ${string}"
        def learnsScala(): String = learns("Scala")
        def apply(times: Int): String = s"${this.name} watched ${this.favoriteMovie} ${times} times"
    }

    val mary = new Person("Mary","Inception")
    println(mary.likes("Inception"))

    // infix notation = operator notation. Isso só pode ser feito com métodos de só 1 parâmetro
    println(mary likes "Inception")
    //objeto método parâmetro
    //"operators"
    val tom = new Person("Tom","Fight Club")
    //"operators" pq o método serve como um operador entre dois itens que retorna um terceiro
    println(mary hangOutWith tom)

    //Todos os operadores são métodos, posso definir um método com um operador
    println(mary + tom)
    println(mary.+(tom))         // mesma coisa

    // prefix notation
    val x = -1
    val y = 1.unary_-
    println(x==y)                // mesma coisa

    // unary_ prefix funciona só com + - ~ !
    println(!mary)

    // postfix notation, só funciona com funções sem parâmetros
    println(mary.isAlive)
    println(mary isAlive)

    // apply
    println(mary.apply())
    println(mary())      //Quando uma instância é chamada como se fosse uma função, a chamada vai pro método apply() da classe


    /* Exercícios */
    val tito = new Person("Tito","Endgame")
    val rock_tito = tito + " O poderoso"
    println(rock_tito())
    println((tito + " O poderoso")())      // mesma coisa

    val claudemir = new Person("Claudemir","Interstelar",22)
    val inc_claudemir = +claudemir
    println((+claudemir).age)      //ASSIM FUNFA
    println(inc_claudemir())       // ASSIM FUNFA
    println((+claudemir).apply())  // ASSIM FUNFA
    println((+claudemir)())        // ASSIM N FUNFA

    println(claudemir learnsScala)                 //postfix
    println(claudemir learns "Eng. de Dados")      //infix (operator)

    println(tito(3))
    println(claudemir(15))
}
