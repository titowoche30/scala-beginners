package lectures.part1basics

object ValuesVariablesTypes extends App {
    val x: Int = 314                    //4 bytes
    val y = 321.43
    println(x,y)

    //vals são immutable e seus tipos são opcionais, o compilador infere os tipos.

    val aString: String = "Olha a string"
    val bString = "Olha outra string"

    val aBoolean: Boolean = false
    val aChar: Char = 'd'
    val aShort: Short = 123                         //int de 2 bytes
    val aLong: Long = 1234565454545454L            //int de 8 bytes
    val aFloat: Float = 234.54F
    val aDouble: Double = 43434.34443434

    // (Short, Int, Long, Float, Double, Char, String e Boolean)
    //(BigInt,BigDecimal) => Esses são classes da biblioteca padrão do scala
    var aVariable: Int = 14
    var bVariable = 45
    aVariable = 34          //side effects

    println(aVariable,bVariable)
}
