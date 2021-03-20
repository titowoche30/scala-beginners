package lectures.part1basics

object StringOps extends App {
    val str: String = "Hello,downtown"

    //Nesse primeiro bloco é tudo do Java
    println(str.charAt(2))
    println(str.substring(0,5))     //[0,5)
    println(str.split(',').toList)
    println(str.startsWith("Hello"))
    println(str.replace(',','-'))
    println(str)                                 //Não é inplace
    println(str.toLowerCase())
    println(str.length)

    //Agora é scala
    val aNumberString = "23"
    val aNumber = aNumberString.toInt
    println(str.reverse)
    println(str.take(3))

    //S-interpolator
    val name = "Tito"
    val age = 22
    val greeting = s"Hello, my name is $name and I am $age years old"
    println(greeting)
    val anotherGreeting = s"Hello, my name is ${name} and I will be turning ${age + 1} years old"
    println(anotherGreeting)
    // Dentro do {} pode meter qualquer expression

    //F-interpolators(pra formatar tipo um printf e ainda faz s-interpolator, ou seja, é o f-string do python hehe)
    val speed = 1.2f
    val myth = f"${name} can eat ${speed}%2.2f burguers per minute na idade de ${age +1}"
    println(myth)

    //raw-interpolator(printa literal)
    println(raw"this is a \n newline")

}
