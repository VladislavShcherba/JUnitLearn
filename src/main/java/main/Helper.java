package main;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Helper {

    public static void throwRuntimeException() {
        throw new RuntimeException();
    }

    public static void sleepFor(int millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static int divide(int a, int b) {
        return a/b;
    }

    public static boolean returnsFalse() {
        return false;
    }

    public static String methodInvocationInfo() {
        return LocalTime.now() + " " + Thread.currentThread();
    }
}
