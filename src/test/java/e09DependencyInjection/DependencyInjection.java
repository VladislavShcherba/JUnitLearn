package e09DependencyInjection;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.util.HashMap;

// TestInfo -> TestInfoParameterResolver
@Tag("ClassTag1")
@Tag("ClassTag2")
@DisplayName("This is TestInfoTest")
class TestInfoTest {

    static void print(TestInfo testInfo) {
        System.out.println("Test class: " + testInfo.getTestClass().map(Class::getSimpleName).orElse(StringUtils.EMPTY));
        System.out.println("Test method: " + testInfo.getTestMethod().map(Method::getName).orElse(StringUtils.EMPTY));
        System.out.println("Display name: " + testInfo.getDisplayName());
        System.out.println("Tags: " + testInfo.getTags());
        System.out.println("-".repeat(40));
    }

    TestInfoTest(TestInfo testInfo) {
        print(testInfo);
    }

    @BeforeAll
    static void beforeAll(TestInfo testInfo) {
        print(testInfo);
    }

    @AfterAll
    static void afterAll(TestInfo testInfo) {
        print(testInfo);
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        print(testInfo);
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        print(testInfo);
    }

    @Test
    void test1(TestInfo testInfo) {
        print(testInfo);
    }

    @Test
    @Tag("MethodTag1")
    @DisplayName("This is test2")
    void test2(TestInfo testInfo) {
        print(testInfo);
    }
}

// RepetitionInfo -> RepetitionInfoParameterResolver
// see e11RepeatedTests

// TestReporter -> TestReporterParameterResolver
//TODO: understand how to use TestReporter
class TestReporterTest {
    @Test
    void test1(TestReporter testReporter) {
        testReporter.publishEntry("value");
        testReporter.publishEntry("key1", "value1");
        testReporter.publishEntry(new HashMap<>() {{
            put("key2", "value2");
            put("key3", "value3");
        }});
    }
}