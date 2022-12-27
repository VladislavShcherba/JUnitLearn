package p02WritingTests.e09DependencyInjection;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

//Example of custom ParameterResolvers that resolves String and int parameters for constructors
@ExtendWith(StringConstructorResolver.class)
@ExtendWith(IntConstructorResolver.class)
class ATest {

    private String string;
    private int integer;

    ATest(String string, int integer) {
        this.string = string;
        this.integer = integer;
    }

    @Test
    public void test1() {
        System.out.println("Inside ATest.test1(), string = " + string);
    }

    @Test
    public void test2() {
        System.out.println("Inside ATest.test2(), integer = " + integer);
    }
}

@ExtendWith(StringConstructorResolver.class)
class BTest {

    private String string1, string2;

    BTest(String string1, String string2) {
        this.string1 = string1;
        this.string2 = string2;
    }

    @Test
    public void test1() {
        System.out.println("Inside BTest.test1(), string1 = " + string1);
    }

    @Test
    public void test2() {
        System.out.println("Inside BTest.test2(), string2 = " + string2);
    }
}

class StringConstructorResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        System.out.println("Inside StringConstructorResolver.supportsParameter()");
        System.out.println("Parameter: " + parameterContext.getParameter());
        System.out.println("Declaring Executable: " + parameterContext.getDeclaringExecutable());
        System.out.println("Element: " + extensionContext.getElement().get());
        System.out.println("Exiting StringConstructorResolver.supportsParameter()");

        Executable executable = parameterContext.getDeclaringExecutable();
        Class<?> parameterType = parameterContext.getParameter().getType();
        return executable instanceof Constructor<?> && parameterType == String.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        System.out.println("Inside StringConstructorResolver.resolveParameter()");
        System.out.println("Parameter: " + parameterContext.getParameter());
        System.out.println("Declaring Executable: " + parameterContext.getDeclaringExecutable());
        System.out.println("Element: " + extensionContext.getElement().get());
        System.out.println("Exiting StringConstructorResolver.resolveParameter()");
        return "Yes!";
    }
}

class IntConstructorResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        System.out.println("Inside IntConstructorResolver.supportsParameter()");
        System.out.println("Parameter: " + parameterContext.getParameter());
        System.out.println("Declaring Executable: " + parameterContext.getDeclaringExecutable());
        System.out.println("Element: " + extensionContext.getElement().get());
        System.out.println("Exiting IntConstructorResolver.supportsParameter()");

        Executable executable = parameterContext.getDeclaringExecutable();
        Class<?> parameterType = parameterContext.getParameter().getType();
        return executable instanceof Constructor<?> && parameterType == int.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        System.out.println("Inside IntConstructorResolver.resolveParameter()");
        System.out.println("Parameter: " + parameterContext.getParameter());
        System.out.println("Declaring Executable: " + parameterContext.getDeclaringExecutable());
        System.out.println("Element: " + extensionContext.getElement().get());
        System.out.println("Exiting IntConstructorResolver.resolveParameter()");
        return 100;
    }
}