package p02WritingTests.e11RepeatedTests;

import org.junit.jupiter.api.*;

class MyRepeatedTest {

    @BeforeEach
    void beforeEach(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println("Going to execute " + testInfo.getDisplayName());
        System.out.println("Repetition " +repetitionInfo.getCurrentRepetition() +
                " of " + repetitionInfo.getTotalRepetitions());
    }

    @DisplayName("Test 1")
    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    void test1() {}

    @DisplayName("Test 2")
    @RepeatedTest(value = 5, name = "{displayName} -> ({currentRepetition}/{totalRepetitions})")
    void test2() {}
}
