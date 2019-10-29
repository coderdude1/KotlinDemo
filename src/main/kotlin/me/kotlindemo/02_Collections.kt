package me.kotlindemo

import me.kotlindemo.classes.Beer

fun main() {
    iterateCollection()
    lambdaFilterAndSearch()
    collectionMutability()
    someMapStuff()
    usingAnInitiailzerFunction()
}

//note that these are var, but the underlying collections are immutable (see below)
var setOfTaprooms = setOf("Roets", "Badger Hill", "Lucid", "Modist", "Lupin") //type is inferred to be string
var listOfBeers = listOf("Dreamyard", "Bearded Lad", "Freethought", "Traitor IPA")
val mutableNumbers = mutableListOf("one", "two", "three")  //list is mutable, but the REFERENCE is not, ie can't do the following
//mutableNumbers = mutableListOf() //this fails compile error.  Uncommnet to show
val beers = listOf(
    Beer("Roets", "Roets IPA", 6.1, 62, "IPA"),
    Beer("Roets", "Berry", 5.8, 21, "Sour"),
    Beer("Badger Hill", "Traitor IPA", 5.5, 71, "IPA"),
    Beer("Castle Danger", "Castle Cream Ale", 5.5, 0, "Cream Ale"),
    Beer("Boathouse Brothers","Candy Cove",5.3, 12 , "Kolsch"),
    Beer("Wild Mind Ales", "Skateboard Guitar", 6.3, 10, "Sour"))

//key to value
val mapOfNumbers = mapOf(1 to "one", 2 to "two", 3 to "three")

fun iterateCollection() {
    println("\n>>>>  Iterate Collection")
    for (tapRoom in setOfTaprooms) { //for is not an expression can't do iterateCollection() = ...
        println("taproom: $tapRoom")
    }

    for (beer in listOfBeers) {
        println("beer: $beer")
    }
}

fun lambdaFilterAndSearch() {
    println("\n>>>>  lambdaSliceAndDice")
    setOfTaprooms.filter { it.startsWith("l", ignoreCase = true) } //note no () for method call if lambda is last/only arg
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach{println(it)}

    println("    now do same on a list of objects")
    beers.filter { it.ibu > 10 }
        .sortedBy { it.abv }
        .map { it.brewer + ": " + it.name }
        .forEach{println(it)}
}

fun collectionMutability() {
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
    for((key, value) in mapOfNumbers) {  //destructuring
        println("    key: $key value: $value")
    }
}

fun usingAnInitiailzerFunction() {
    println("  \nInit a list")
    val listOInts = List(30) {it + 1}  //it is the index of the element
    println("    list populated via it + 1 initialzer $listOInts")

    val listFromAnotherInitializer = List(30) {it}
    println("    list populated via a it initialzer $listFromAnotherInitializer")
    //note the 2 different initializers are the same

    val listFromRange = (1..30).toList()
    println("    list populated by a range:    $listFromRange")

    //sequences are lazy by default, in this case the toList does cause it to not be lazy or mem efficiant
    val fromASequence = generateSequence(1)  { it + 1 }.takeWhile { it <= 30 }.toList()
    println("    List populated via a sequence: $fromASequence")
}

//    val numbers: List<Int> = emptyList() //readonly empty list.
//    val mutableList = mutableListOf<Int>() //need a mutable list to add to it.
