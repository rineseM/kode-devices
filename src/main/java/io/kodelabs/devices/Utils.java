package io.kodelabs.devices;

public class Utils {
    private static String Id = "10000";

    public static String generateNextId() {
        int number = Integer.valueOf(Id);
        number++;
        Id = String.valueOf(number);
        return Id;
    }
}
