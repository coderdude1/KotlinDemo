package me.kotlindemo.classes

/**
 * Data classes are great for simple POJOs or DTOs, ie stuff that are simple data containers, not biz logic.  You
 * get a lot of boilerplate code (getter/setters, hashcode, equals, copy, toString) without writing it
 *
 * note firstName is var to allow setting it in java
 *
 * just lightly covering this, there are companion objects and Sealed classes too
 *
 * TODO
 * 1. getter and setter overrides. https://kotlinlang.org/docs/reference/properties.html
 * 2. backing fields https://kotlinlang.org/docs/reference/properties.html
 * 3.  lateinit (stuff thats not null in constructor must be set on construction, except for lateInit
 */
data class Person(val id: String, var firstName: String, val lastName: String, val age: Int = 31) //note age has default value, makes it optional in constructor

fun main() {
    showDataClass()
}

fun showDataClass() {
    val one = Person("one", "fname", "lname")
    val two = Person("one", "fname", "lname", 31)
    val three =
        Person(id = "one", firstName = "fname", lastName = "lname")//note use of named params
    println("equals one and three: ${one.equals(two)}")
    println("toString: $one")
    println("field ${one.age}")
}

class Parent(count: Int)
//class child(count: Int) : Parent(count)//fails with compile time check due to parent not open

open class OpenParent(count: Int) {
    fun someFunction() {}
    open fun extendableFunction() {println("count")}
}

class openChild(count: Int) : OpenParent(count) {
    override fun extendableFunction() {println("extended count ")}
}
