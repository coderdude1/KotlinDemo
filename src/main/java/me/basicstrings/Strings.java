package me.basicstrings;

public class Strings {
    public static void main(String[] args) {
        Strings strings = new Strings();
        strings.basicEquality();
    }

    private void basicEquality() {
        String one = "one";
        String nullString = null;
        System.out.println("Not null equals null string: " + one.equalsIgnoreCase(null));
//        System.out.println("null equals not null: " + nullString.equalsIgnoreCase(one)); //NPE
    }
}
