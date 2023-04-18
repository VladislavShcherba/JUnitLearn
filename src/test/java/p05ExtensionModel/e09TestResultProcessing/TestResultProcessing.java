package p05ExtensionModel.e09TestResultProcessing;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

@ExtendWith(MyTestWatcher.class)
class ATest {

    @Test
    void test1() {
        System.out.println("test1()");
    }

    @Test
    void test2() {
        System.out.println("test2()");
        Assertions.fail();
    }

    @Disabled("Some reason")
    @Test
    void test3() {
        System.out.println("test3()");
    }

    @Test
    void test4() {
        System.out.println("test4()");
        throw new RuntimeException();
    }
}

@ExtendWith(MyTestWatcher.class)
class BTest {

    @BeforeEach
    void beforeEach() {
        Assertions.fail();
    }

    @Test
    void test1() {
        System.out.println("test1()");
    }
}

@ExtendWith(MyTestWatcher.class)
class CTest {

    @BeforeAll
    static void beforeAll() {
        Assertions.fail();
    }

    @Test
    void test1() {
        System.out.println("test1()");
    }
}

@ExtendWith(MyTestWatcher.class)
class DTest {

    @AfterAll
    static void afterAll() {
        System.out.println("DTest.afterAll()");
    }

    @Test
    void test1() {
        System.out.println("test1()");
        Assertions.fail();
    }
}

class MyTestWatcher implements TestWatcher {
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("MyTestWatcher.testDisabled()");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("MyTestWatcher.testSuccessful()");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("MyTestWatcher.testAborted()");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("MyTestWatcher.testFailed()");
    }
}
