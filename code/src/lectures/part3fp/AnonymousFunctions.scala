package lectures.part3fp

object AnonymousFunctions extends App{
    //Modo OO de instanciar uma anonymous function(Lambda)
    val doubler = new ((Int) => Int) {
        override def apply(v1: Int): Int = v1*2
    }
    println(doubler(3))

    //Jeito Scala
    val doubler2 = (x: Int) => x * 2            //Instancia uma Function1[Int,Int] e faz um apply
    val doubler3: (Int => Int) = (x => x*2)     //Mesma coisa

    val adder:((Int,Int) => Int) = ((x,y) => x+y)
    val adder_1 = (x:Int,y:Int) => x+y

    val justSomething = () => 3
    println(justSomething)          //É a própria função lambda. Lambda é uma subclasse de anonymous
    println(justSomething())        //É o apply mesmo

    val subtracter = { (x: Double, y: Double) =>
        x - y
    }
    println(subtracter(3.4,7.8))
    println(subtracter)                 //Continua sendo uma lambda

    //Esse de baixo só funciona se definir o tipo da val

    val incrementador: Int => Int = _ + 1    //O mesmo de = x => x + 1
    val somador: (Int,Int) => Int = _ + _    //O mesmo de (x,y) => x + y
    println(incrementador(34))
    println(somador(78,2))

    /*EXERCÍCIO
        Substituir isso
            val specialFunction_1: ((Int) => ((Int) => Int)) = new ((Int) => ((Int) => Int)) {
        override def apply(v1: Int): ((Int) => Int) = new ((Int) => Int){
            override def apply(v2: Int): Int = v1 + v2
        }
    }
        Por lambda
     */

    //Isso são curried functions
    val specialFunction_1: (Int => (Int => Int)) = (v1 => (v2 => v1 + v2))
    val specialFunction_2 = (v1:Int) => ((v2:Int) => v1 + v2)         //Mesma coisa
    println(specialFunction_1(2)(3))
    println(specialFunction_2(3)(2))
}
