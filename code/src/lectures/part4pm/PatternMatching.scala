package lectures.part4pm

import scala.util.Random

object PatternMatching extends App{
    //Switch tunado

    val random = new Random
    val x = random.nextInt(10)
    println(x)

    val description = x match{
        case 1 => "Deu 1 hein"
        case 2 => "Deu 1+1 hein"
        case 3 => "Deu 3 hein"
        case n if n % 2 == 0 => "par hein"
        case _ => "Default hein"         //_ é um Wildcard
    }
    println(description)

    // 1. Decompose values
        //Case classes podem ser deconstructed or extracted in pattern matching
        //classes normais tbm, mas as case já vem o bagulho pronto.

    case class Person(name:String,age:Int)

    val bob = Person("Bob",31)

    //Consegue extract os values da instância de uma case class
    val greeting = bob match {
        case Person(n,a) if a < 34 => s"Hello, I'm ${n}, I'm ${a} years old and I'm young"
        case Person(n,a) => s"Hello, I'm ${n} and I'm ${a} years old :)"     //Se bob for instância disso
        case _ => "AAAAAAAAAAAAAAAAAAA"
    }
    println(greeting)

    //1. Cases are matched in order(o primeiro que pegar retorna)
    //2. Se ninguém der match, throw um MatchError
    //3. O tipo da expressão é o tipo combinado dos retornos, se for tudo str, é str, se for int e str ele
    // acha o ancestral mais perto deles 2

    //Em hierarquias de sealed classes
    //Sealed classes = Classe que só pode ser herdada por classes no mesmo arquivo.
    //Ela é a soma de seus tipos derivados, tipo Option que é a soma de Some[A] e None

    sealed class Animal
    case class Dog(breed:String) extends Animal
    case class Passarin(greeting: String) extends Animal

    val animal:Animal = Passarin("piu piu")
    //val passarin:Passarin =

    //O tipo da variável do match tem que poder instanciar os cases, ou seja, ser pai
    animal match {
        case Passarin(greet) => println(s"Passarin que faz $greet")
        case Dog(breed) => println(s"Cachorrin de $breed")

    }

    //match everything
    val y = 16
    val isEven = y match{
        case n if n%2==0 => true
        case _ => false
    }
    println(isEven)

    //EXERCÍCIOS

    trait Expr
    case class Number(n: Int) extends Expr
    case class Sum(e1:Expr,e2:Expr) extends Expr
    case class Prod(e1:Expr,e2:Expr) extends Expr

    def show(expr: Expr):String = {
        expr match {
            case Number(n) => s"$n"
            case Sum(e1,e2) => s"${show(e1)} + ${show(e2)}"
            case Prod(e1,e2) => {
                def maybeParentheses(e: Expr): String = e match {
                    case Prod(_,_) => show(e)
                    case Number(_) => show(e)
                    case _ => "(" + show(e) + ")"
                }
                maybeParentheses(e1) + " * " + maybeParentheses(e2)
            }
        }
    }

    println(show(Sum(Number(2),Number(3))))
    println(show(Prod(Sum(Number(2),Number(1)),Number(3))))
    println(show(Prod(Sum(Number(2),Number(1)),Sum(Number(3),Number(4)))))


}
