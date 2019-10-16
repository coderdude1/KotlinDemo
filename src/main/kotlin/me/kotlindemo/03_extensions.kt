package me.kotlindemo

/**
 * https://kotlinlang.org/docs/reference/extensions.html
 *
 * You can add extensions to 3rd party libs, even if it is unmodifiable.  Kotlin uses extensions to add stuff to the JDK
 * like the Collections api, etc
 *
 * Scope of extensions - typically within the package it's declared in.  if it is to be referenced in different packages
 * it can be imported, ie:
 *     import me.kotlin.basic.basicstart.doodify
 */
fun main() {
    showSimpleExtensionFunction()
}

val randy = "Randy!"

/**
 * Receiver type is the class getting extended, ie 'String' in this example
 * this fun returns a type 'Sring'
 * this will show up in autocomplete
 * ingore intellij warning for now about String. receiver param not used
 */
fun String.doodify() : String = "doood!"  //adds the fun 'doodify' to all string objects in this module, can use block body too

//infix requires a single param for the target.  allows calling without a '.(', ie "s" superdoodify
infix fun String.superdoodify(numberOfDoods: Int) : String  {
    var result = "d"
    for (counter in 1 .. numberOfDoods) {
        result += "oo"
    }
    return result + "d" //return required for block body
}

fun showSimpleExtensionFunction() {
    println("  simple extension on a string: $randy is now ${randy.doodify()}") //ctrl hover over doodify to see what it is
    println("  infix extension: ${randy superdoodify(14)}") //note no .(
}
