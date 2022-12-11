package p02WritingTests.e03Assumptions;

import main.Helper;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class AssumptionsTest {

    @Test
    void assumeTest() {
        assumeTrue(System.getProperty("os.name").toLowerCase().contains("z/os"),
                "This is not z/OS");
    }

    @Test
    void assumingThatTest() {
        assumingThat(LocalDate.now().isAfter(LocalDate.of(1999, 1, 30)),
                () -> assertEquals(1, Helper.divide(4, 2)));
    }
}