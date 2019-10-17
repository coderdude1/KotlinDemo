package me.kotlindemo

import java.lang.NumberFormatException
import kotlin.IllegalArgumentException

/** note that package name  does not have to match path on disk
 * default scope is public, other scopes are internal (module), private, protected
 * TODO
 * 1.  Destructuring https://kotlinlang.org/docs/reference/multi-declarations.html
*/

fun main() {
    functions()
    someVariableStuff()
    conditionals()
    loops()
    nullableStuff()
    exceptions()
}

/**
 *  FUNCTIONS
 *  Functions in kotlin are 'first class objects', ie the can be assigned to variables, passed as params
 *  to other functions, and returned as a result from higher order functions.
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
//expression body syntax, return type is inferred.  single line only (no {})
fun add(a: Int, b: Int) = a + b

//return type is required when using {}, multiline
fun addWithBody(a: Int, b: Int): Int {
    return a + b
}

//no return type, //implicitly returns Unit
fun addAndPrint(a: Int, b: Int) = println("  fun with expression body: printing sum: ${a + b}")


fun addAndPrintBody(a: Int, b: Int) {
    println("\n  fun with block body: printing from a body with no return: ${a + b}")
    return Unit //not required, can be ignored
}

fun paramsWithDefaultValues(one: String = "blah", two: Boolean = false, three: Int = 0,
                            fourNullable: String? = null){  //one, two, three are not nullable.  four is
    println("\n  fun params with default values:  one: $one two:$two three: $three, fourNullable: $fourNullable")
}

/**
 *  Lambdas
 *  Similar to the way java does them for the most part
 */
//Lambda Expressions
//return type is inferred, ie we don't explicitly say 'Int'
val sumTypeInferred = {x: Int, y: Int -> x + y}
val aResult = sumTypeInferred(3, 4)

//Explicit type declaration
// Format: (input params) -> return type = implementation
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
 */
fun someVariableStuff() {
    println("\n>>>>> Variables")
    val implicitInt = 32 //type is implicit Int, val means the reference can only be set once
//    implicitInt = 33 //compile time fail uncomment to show
//    implicitInt = "string" //compile time reassign val error AND type mismatch error
    var varInt = implicitInt //valid, var can have it's reference changed
    println("  varInt = $varInt")
    println("  varInt with ++ = ${++varInt}") //need {} to get ++ to work, ie an expression
    println("  varInt after increment = $varInt")
}

/**
 *  Conditionals
 */
//IF as an expression so no ternary in kotlin (not same as elvis) ie if statements are expressions and return a value
fun ifAsAnExpressionMaxValue(a: Int, b: Int) = if (a > b) a else b

fun ifExpressionReturnsAValueWithBlocks(a: Int, b: Int): Int =
    if (a > b) { //if this was a block body, return requred here on the if since if is an expression.  return is required for block functions vs expression body
        println("  if: a is bigger $a > $b")
        a //return a
    } else {
        println("  if: b is bigger $b > $a")
        b //return b
    }

//when replaces switch.  when can be used as an expression or a statement, excuted sequentially returns a string
fun simpleWhenAsExpression(a: Int) =
    when(a) {
        in 0..5 -> "  range: a in 0..5 range: $a"
        else -> "  range: a is out of range $a"
    }

//implicitly returns Unit
fun whenWithSmartCast(a: Any) =
    when(a) {
        is String -> println("  smartcast: got a string: $a") //in block we don't have to do typcast, its automagic
        else -> println("  smartcast: not a string $a")
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
}

/**
 * >>>> Loops
 */

fun simpleForLoopOverRange() {
    println("\n  For loop over a range")
    for (counter in 1..5) println("    Counter $counter")
}

fun progression() {
    println("\n  loop with progression of 2 and a range")
    for(counter in 1..10 step 2) println("    progression of 2: $counter")
}

fun loops() {
    println("\n>>>> Loops")
    simpleForLoopOverRange()
    progression()
}

/**
 * >>>>  NULLABLE STUFF
 */

fun nullableParams(one: String? = "blah", two: String? = null) { //? means this param is nullable,
    println("  nullableParamsToFun: fun with nulllable props one: $one two: $two")
}

fun safeCallOperator(one: String?) {
//    println("one: ${one.toUpperCase()}") //can throw an NPE since one is nullable, compile time warning
    println("  safeCallOperator: one: ${one?.toUpperCase()}") //? is safecall, ie if not null then toUpperCase(), can be chained ie x?.y?.z?
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

fun allowNPE(nulledString: String? = null) {
    nulledString!!.length //throw the npe
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



