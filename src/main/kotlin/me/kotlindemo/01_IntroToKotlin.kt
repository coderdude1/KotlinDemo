package me.kotlindemo

import me.kotlindemo.classes.Person
import java.lang.NumberFormatException
import kotlin.IllegalArgumentException

/** note that package name  does not have to match path on disk
 * default scope is public, other scopes are internal (module), private, protected.
 * A module is everything that is in a single compile unit
 *
 * TODO
 * 1.  Destructuring https://kotlinlang.org/docs/reference/multi-declarations.html
*/

/**
 * this looks familiar!  intellij autocomlentes if you start typing 'main'
 */
fun main() {
    someVariableStuff()
    functions()
    conditionals()
    loops()
    nullableStuff()
    exceptions()
}

/**
 *  FUNCTIONS
 *  Functions in kotlin are 'first class objects', ie the can be assigned to variables, passed as params
 *  to other functions, and returned as a result from higher order functions.  They do not need to be defined
 *  in a class scope.
 *
 *  if a function has a 'void' return type, in kotlin use 'Unit'.  The compiler will implicit return 'Unit' if not declared
 *  expression body vs block body
 */
fun functions() {
    println("\n>>>>>  Some functions")
    println("  call add ${add(1, 3)}") //note string template
    println("  call addWithBody ${addWithBody(9, 17)}") //string template
    addAndPrint(14, 3)
    addAndPrintBody(17, 43) //cmd-click to show Unit is returned
    paramsWithDefaultValues() //use defaults
    paramsWithDefaultValues(
        "one",
        false,
        14
    )//note four is using default, declard in order without names
    paramsWithDefaultValues(two = false) //named params do not have to be in order
    doSomethngWithLambdas()
}

//functions are first class and do not need to be part of a class
//this is expression body syntax, return type is inferred.  single line only (no {})
fun add(a: Int, b: Int) = a + b

//return type is required when using {}, multiline
fun addWithBody(a: Int, b: Int): Int {
    return a + b
}

//no return type, //implicitly returns Unit
fun addAndPrint(a: Int, b: Int) = println("  fun with expression body: printing sum: ${a + b}")

//return type not needing to be specified, so it's implicit Unit
fun addAndPrintBody(a: Int, b: Int) {
    println("\n  fun with block body: printing from a body with no return: ${a + b}")
    return Unit //not required, can be ignored
}

fun paramsWithDefaultValues(one: String = "blah", two: Boolean = false, three: Int = 0,
                            fourNullable: String? = null) {  //one, two, three are not nullable.  four is
    println("\n  fun params with default values:  one: $one two:$two three: $three, fourNullable: $fourNullable")
}

/**
 * Function params are val (immutable).  Compile time errors result if you 'var' it
 */
//fun paramsAreDefaultVal(inputString: String. var anotherInputString: String) { //compile error on var, 'expecting type name'
////    inputString = "blah" //val cannot be reassigned compile error
//}

/**
 *  Lambdas
 *  Similar to the way java does them for the most part, with a bit less verbage
 */
//return type is inferred, ie we don't explicitly say 'Int'
val sumTypeInferred = {x: Int, y: Int -> x + y}
val aResult = sumTypeInferred(3, 4)

//Explicit type declaration
// Format: (input params) -> return type = {implementation}
val printNumber : (Int) -> Unit = {num -> println("    explioct type declared for lambda num: $num")}
// the following doesn't work even tho I found lots of examples with it
//val printNumberErrors : Int -> Unit = {num -> println("    explioct type declared for lambda num: $num")}

fun doSomethngWithLambdas() {
    println("\n>>>>>  do a couple of lambda things")
    println("  invoke a lambdaExpression 3 + 4 = ${sumTypeInferred(3, 4)}")//need ${} since params, even tho it is a 'property'
    printNumber(14)
}

/**
 *  VARIABLES
 *  https://kotlinlang.org/docs/reference/basic-types.html
 *
 *      For the most part from a dev view, everything is an object.  At runtime things may have a primitive value, but we always
 *      have access to member functions to these types (ie int is always Int)
 *      variables are a reference
 *      you can have immutable references (val) and mutable references (var)
 *      Types do not need to be specified, they can be determined at time of assignment, ie inferred.
 *      You can explicitly declare types if you want.
 *      Once a type has been set, it can't be changed, ie a var set to a string cannot be assigned to an int
 *
 *      The use of val is encouraged.  Immutability is default for a lot of things
 *
 *      Numbers are Byte (8bits), Short (16), Int (32) and Long(64).  all are signed
 *      Floating point numbers are Float (32) and Double(64).  Decimals are by default Double.  If you need larger precision
 *      or money (or scienece stuff) use BigDecimal
 *      Others are String,, Char (16) and Boolean (8)
 */
fun someVariableStuff() {
    println("\n>>>>> Variables")

    val implicitInt = 32_000 //type is implicit Int, val means the reference can only be set once, not underscore allowed to make more readable
    val numWitDecimals = 32.0132 //type is Double
    val floatWithDecimal = 32.013f //trailing 'f' makes it a Float
    println("  some decimals ${numWitDecimals.javaClass}=$numWitDecimals and ${floatWithDecimal.javaClass} is $floatWithDecimal")
//    implicitInt = 33 //compile time fail uncomment to show
//    implicitInt = "string" //compile time reassign val error AND type mismatch error
    var varInt = implicitInt //var has mutable reference, but once assigned a type it can't change
    println("  varInt = $varInt values equal: ${varInt == implicitInt}") //string interpolation, property so no {}, note no .equals
//    varInt = "blah" //type mismatch, once a var has a type assigned it can't change
    varInt = implicitInt * 2 //var has mutable reference, so we can reassign it
    println("  after changing reference varInt = $varInt")
    println("  varInt with ++ = ${++varInt}") //need {} to get ++ to work, ie an expression
    println("  varInt after increment = $varInt")
}

/**
 *  Conditionals
 */
//IF as an expression so no ternary in kotlin (not same as elvis) ie if statements are expressions and return a value
fun ifAsAnExpressionMaxValue(a: Int, b: Int) = if (a > b) a else b

fun ifExpressionReturnsAValueWithBlocks(a: Int, b: Int): Int { //block body
    return if (a > b) { //if is an expression thus returns the value
        println("  if: a is bigger $a > $b")
        a //return a, 'return' not required since this is an expression body syntax, ie no {} for the block
    } else {
        println("  if: b is bigger $b > $a")
        b //return b
    }
}

//when replaces switch.  when can be used as an expression or a statement, executed sequentially returns a string
fun simpleWhenAsExpression(a: Int) =
    when(a) {
        in 0..5 -> "  range: a in 0..5 range: $a"
        else -> "  range: a is out of range $a"
    }

/**
 * implicitly returns Unit
 * no need for casting after figuring type, ie no if(instanceof String) {String x - (String)obj
 */
fun whenWithSmartCast(a: Any) =
    when(a) {
        is String -> println("  smartcast: got a string: $a") //in block we don't have to do typecast, its automagic
        else -> println("  smartcast: not a string $a")
    }

fun someFunWithEquals() {
    println("  Showing equals")
    val personOne = Person("blah", "Johnny", "Walker")
    val personTwo = Person("blah", "Johnny", "Walker")
    println("    using == is same as .equals testing structural equality: ${personOne == personTwo}")  //== in place of .equals
    println("    using === tests for referential equality: ${personOne === personTwo}")
}

//implicitly returns Unit
fun conditionals() {
    println("\n>>>> Conditionals")
    println("  if as an expression, max value of 8 anc 2 is ${ifAsAnExpressionMaxValue(8, 2)}")
    println("  if expression with blocks biggest of 13 and 53 is ${ifExpressionReturnsAValueWithBlocks(
        8,
        2
    )}")
    println("  simple when as an expression ${simpleWhenAsExpression(2)}")
    println("  simple when as an expression out of range ${simpleWhenAsExpression(44)}")
    whenWithSmartCast("  smartCast with string 1")
    whenWithSmartCast(1)
    someFunWithEquals()
}

/**
 * >>>> Loops
 */

fun simpleForLoopOverRange() {
    println("\n  For loop over a range")
    for (counter in 1..5) println("    Counter $counter")
}

/**
 * >>>>> Ranges
 */
fun doingStuffWithRanges() {
    println("\n>>>>  stuff with ranges")
    print("    typical for loop\n      ")
    for (i in 1..20) { print("$i,") }

    print("\n  using until for loop\n      ")
    for (i in 0 until 20) { print("$i,") }

    print("\n\n  using a progression of 2 for loop\n      ")
    for (i in 2..10 step 2) { print("$i,") }//progression of 2

    print("\n\n    Counting downward\n      ")
    for (i in 10 downTo 1) { print("$i,") }

    print("\n\n    Counting downward with a step\n      ")
    for (i in 10 downTo 1 step 2) { print("$i,") }

    print("\n\n    Counting downward differently doesn't work\n      ")
    for (i in 10..1) { print("$i,") } //note the intellij highlighting asking to use a downTo

    print("\n\n    using range in a if statement\n      ")
    val somenum = 8
    if (somenum in 1..10) { println("$somenum is in the range") }
}

fun loops() {
    println("\n>>>> Loops")
    simpleForLoopOverRange()
    doingStuffWithRanges()
}

/**
 * >>>>  NULLABLE STUFF
 * by default kotlin expects everything to be not null.  it won't let you assign stuff to be null
 *
 * if you want something nullable you have to expliclity indicate a property is nullable.  Kotlin will then
 * require any code using it to declare nullability
 */

fun nullableParams(one: String? = "blah", two: String? = null) { //? means this param is nullable,
    println("  nullableParamsToFun: fun with nulllable props one: $one two: $two")
}

//val blah: String = null //compile error since blah is defined by default as non-null
fun nullableAssignments() {
    var nullableString: String? = "blah"
//    nullableString.length //compile error about nullableString can be null and you need to do stuff
    var lengthOfString = nullableString?.length //use safecall to access length

    //Elvis operator ?: used for null checks
    val stringAllCaps = nullableString?.capitalize() ?: "not today"
}

fun safeCallOperator(one: String?) {
//    println("one: ${one.toUpperCase()}") //can throw an NPE since one is nullable, compile time warning
    println("  safeCallOperator: one: ${one?.toUpperCase()}") //? is safecall, ie if not null then toUpperCase(), can be chained ie x?.y?.z?
}

/**
 * Kotlin requires you to explicitly allow an NPE to be thrown, by default it forces you to deal with it
 * using the safecall/elvis (or let/apply blocks) which do not throw an NPE
 */
fun allowNPE(nulledString: String? = null) {
//    nulledString?.length ?: throw java.lang.NullPointerException() //same as following line
    nulledString!!.length //throw the npe same as previous line
}

fun nullableStuff() {
    println("\n>>>>Nullable stuff")
    nullableParams(one = null)
    safeCallOperator(null)
    try {
        allowNPE()
    } catch (npe: NullPointerException) { //note the order
        println("Caught an NPE")
    }
}

/**
 *   EXCEPTIONS
 *      kotlin does not support Checked Exceptions
 *      it's a bit more complicated when interacting with java
 */
fun exceptions() {
    tryIsAnExpression("22")
    tryIsAnExpression("thisis not an int")
    throwIsAnExpressionWithElvis("valid value")
    throwIsAnExpressionWithElvis(null)
}

fun tryIsAnExpression(input: String) {
    val inputInt = try {
        input.toInt()
    } catch (nfe: NumberFormatException) {
        0
    }
    println("  tryIsAnExpression: input: $input inputInt: $inputInt")
}

fun throwIsAnExpressionWithElvis(input: String?) {
    try {
        val result = input
            ?: throw IllegalArgumentException("null input") //elvis is fancy ternary for null checks, ie if null then else
        println("  throwIsAnExpression with elvis: $result")
    } catch(iae: IllegalArgumentException) {
        println("  throwIsAnExpression with error")
    }
}



