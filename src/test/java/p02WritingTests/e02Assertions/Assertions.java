package p02WritingTests.e02Assertions;

import main.Helper;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;

import static org.junit.jupiter.api.Assertions.*;

class AssertionsTest {

    static final String NEVER_SHOWN_MESSAGE = "Message shouldn't be shown";

    @Test
    void standardAssertions() {
        assertEquals(1, 1, NEVER_SHOWN_MESSAGE);
        assertEquals(1, 2, "1 is not 2"); // this line throws an exception,
        assertEquals(2, 3, "2 is not 3"); // so this line will not be executed
    }

    @Test
    void groupedAssertions() {
        assertAll("Heading: Grouped assertion",
                () -> assertEquals(2, 4, "2 is not 4"),
                () -> assertEquals(5, 5, NEVER_SHOWN_MESSAGE),
                () -> assertEquals("Hello", "Hello", NEVER_SHOWN_MESSAGE),
                () -> assertEquals(2.0F, 2.001F, 2.0F + " is not " + 2.001F)
        );
    }

    @Test
    void dependantAssertions() {
        String greeting = "Hello!";
        assertAll("Heading: Dependant assertion",
                () -> {
                    assertNotNull(greeting);
                    assertAll("Heading: Only executes if greeting is not null",
                            () -> assertTrue(greeting.startsWith("H")),
                            () -> assertTrue(greeting.endsWith("o"))
                    );
                },
                () -> {
                    assertNull(greeting);
                    org.junit.jupiter.api.Assertions.assertAll("Heading: Only executes if greeting is null",
                            org.junit.jupiter.api.Assertions::fail,
                            org.junit.jupiter.api.Assertions::fail
                    );
                }
        );
    }

    // it counts as an error
    @Test
    void standardAssertionThrowException() {
        assertEquals(0, Helper.divide(1, 0), "1 can not be divided on 0");
    }

    // it counts as a failure
    @Test
    void exceptionAssertions() {
        Exception exception = assertThrows(RuntimeException.class, Helper::throwRuntimeException, NEVER_SHOWN_MESSAGE);
        assertDoesNotThrow(() -> Helper.divide(1, 0), "1 can not be divided on 0");
    }

    @Test
    void timeoutAssertions() {
        assertTimeout(Duration.ofMillis(50),
                () -> Helper.sleepFor(10),
                NEVER_SHOWN_MESSAGE);
        //to be able to stop execution after 10 seconds!
        assertTimeoutPreemptively(Duration.ofSeconds(10),
                () -> new CountDownLatch(1).await(),
                "10 seconds are not enough (it will execute forever!)");
        System.out.println("Here");
    }
}
