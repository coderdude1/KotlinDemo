package me.kotlin.basic.basicstart

fun main() {
    iterateCollection()
    lambdaFilterAndSearch()
    addABeer()
    someMapStuff()
}

//note that these are var, but the underlying collections are immutable (see below)
var taprooms = setOf("Roets", "Badger Hill", "Lucid", "Modist", "Lupin") //type is inferred to be string
var beers = listOf("Dreamyard", "Bearded Lad", "Freethought", "Traitor IPA")
val mutableNumbers = mutableListOf("one", "two", "three")  //list is mutable, but the REFERENCE is not, ie can't do the following
//mutableNumbers = mutableListOf() //this fails compile error.  Uncommnet to show

//key to value
val mapOfNumbers = mapOf(1 to "one", 2 to "two", 3 to "three")

fun iterateCollection() {
    println("\n>>>>  Iterate Collection")
    for (tapRoom in taprooms) { //for is not an expression can't do iterateCollection() = ...
        println("taproom: $tapRoom")
    }

    for (beer in beers) {
        println("beer: $beer")
    }
}

fun lambdaFilterAndSearch() {
    println("\n>>>>  lambdaSliceAndDice")
    taprooms.filter { it.startsWith("l", ignoreCase = true) }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach{println(it)}
}

fun addABeer() {
    println("\n>>>>  mutability of Collection")

//    beers.add("beer") //invalid compile check, immutable list, ie add does not exist
    println("mutableNumbers size: ${mutableNumbers.size}")
    mutableNumbers.add("17")
    println("mutableNumbers size after adding something: ${mutableNumbers.size}")
}

fun someMapStuff() {
    println("\n>>>> maps")
    println("  all Keys: ${mapOfNumbers.keys}")
    println("  all values: ${mapOfNumbers.values}")
    println("  destructuring a map")
    for((key, value) in mapOfNumbers) {
        println("    key: $key value: $value")
    }
}

