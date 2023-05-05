package io.kodelabs.devices;

public class Utils {
    private static String nextId = "10000";
    public String nextId() {
        int number = Integer.valueOf(this.nextId);
        number++;
        nextId = String.valueOf(number);
        return nextId;
    }
}
