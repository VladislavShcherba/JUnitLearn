package p02WritingTests.e10Interfaces;

import org.junit.jupiter.api.*;

@Tag("InheritedTag")
@IndicativeSentencesGeneration(separator = "~>", generator = DisplayNameGenerator.Simple.class)
interface BeforeAndAfterLogger {

    @BeforeEach
    default void beforeEach(TestInfo testInfo) {
        System.out.println("About to execute " + testInfo.getDisplayName());
    }

    @AfterEach
    default void afterEach(TestInfo testInfo) {
        System.out.println("Execution of " + testInfo.getDisplayName() + " has been completed");
    }
}

class InterfaceTest implements BeforeAndAfterLogger {

    @Tag("OneMoreTag")
    @Test
    void test1(TestInfo testInfo) {
        System.out.println("Execution of " + testInfo.getDisplayName());
        System.out.println("Tags " + testInfo.getTags());
    }
}
