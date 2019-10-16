package me.kotlin.basic.basicstart.scopes

/**
 * Note that nothing is attached to a class.  properties and functions are first class objects, ie do not need to be part of
 * a class
 */
fun main() {
    println(nullName)
    println("letUpperCaseWithNull $letUpperCase")
    println("runUpperCaseWithNull $runUpperCase")
//    applyName
}

//Note default scope is public https://kotlinlang.org/docs/reference/visibility-modifiers.html
//private is visible only within the file it is dclared in
//internal - visible within the module
//protected = same as private + visible in subclasses, only applies to classes
internal val nullName = maybeNull(false)
//let takes a lambda, the param is the thing the let is applied on
val letUpperCase = nullName?.let { s -> s.toUpperCase() }  //have to do safe call as nullName may be null
//run supplies the receiver like a 'this'
val runUpperCase = nullName?.run { toUpperCase() }
//apply is like run but returns the receiver allowing chaining. note this prints without invocation
val applyName = nullName?.apply {
        println("apply: ${toUpperCase()}")
    }?.apply { println("apply: ${toLowerCase()}") }


fun maybeNull(x: Boolean): String? =
    if(x) null else "not null string"


