package me.kotlinintegration;

import me.kotlindemo.classes.Person;
import me.kotlindemo._03_extensionsKt;

public class UseAKotlinClass {
    public static void main(String[] args) {
        UseAKotlinClass useAKotlinClass = new UseAKotlinClass();
        useAKotlinClass.printADataClass();
        useAKotlinClass.useAnExtensionFunction();
    }

    private void printADataClass() {
//        Person person = new Person("id", "firstName", "lastName"); //note age with default does not work in java compile time error
        Person person = new Person("id", "firstName", "lastName", 33);
        System.out.println("  Kotlin dataclass Person toString: " + person.toString());
        person.setFirstName("new first name"); //possible since firstName is a var
        System.out.println("  Kotlin dataclass Person toString after changing first name: " + person.toString());
        System.out.println("  Kotlin person explicit getter firstName: " + person.getFirstName());
    }

    private void useAnExtensionFunction() {
        System.out.println("\n  Use a kotlin extension function from java");
        String blah = "blah";
        System.out.println("Using a kotlin extension function: " +_03_extensionsKt.doodify(blah));
    }
}
