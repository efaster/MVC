package model;

public class DisguiseSuitValidator {
    public static boolean isValid(int durability) {
        return !String.valueOf(durability).endsWith("3") && !String.valueOf(durability).endsWith("7");
    }
}