package me.kotlinintegration;

import me.kotlin.basic.basicstart.classes.Person;

public class UseAKotlinClass {
    public static void main(String[] args) {
        UseAKotlinClass useAKotlinClass = new UseAKotlinClass();
        useAKotlinClass.printADataClass();
    }

    private void printADataClass() {
//        Person person = new Person("id", "firstName", "lastName"); //note age with default does not work in java compile time error
        Person person = new Person("id", "firstName", "lastName", 33);
        System.out.println("  Kotlin dataclass Person toString: " + person.toString());
        person.setFirstName("new first name"); //possible since firstName is a var
        System.out.println("  Kotlin dataclass Person toString after changing first name: " + person.toString());
        System.out.println("  Kotlin person explicit getter firstName: " + person.getFirstName());
    }
}
