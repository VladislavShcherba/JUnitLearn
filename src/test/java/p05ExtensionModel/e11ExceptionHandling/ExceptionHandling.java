package p05ExtensionModel.e11ExceptionHandling;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.LifecycleMethodExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;

@ExtendWith(MyTestExecutionExceptionHandler.class)
@ExtendWith(SwallowRuntimeExceptionHandler.class)
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

    @Test
    void test3() {
        System.out.println("test3()");
        throw new RuntimeException();
    }
}

class MyTestExecutionExceptionHandler implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        System.out.print("MyTestExecutionExceptionHandler.handleTestExecutionException()");
        System.out.println(", Throwable: " + throwable);
        throw throwable;
    }
}

class SwallowRuntimeExceptionHandler implements TestExecutionExceptionHandler {

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
        System.out.println("SwallowRuntimeExceptionHandler.handleTestExecutionException()");
        if (throwable instanceof RuntimeException) {
            System.out.println("Swallowed RuntimeException!");
        } else {
            throw throwable;
        }
    }
}

class MyLifecycleExecutionExceptionHandler implements LifecycleMethodExecutionExceptionHandler {

    @Override
    public void handleBeforeAllMethodExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
    }

    @Override
    public void handleBeforeEachMethodExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
    }

    @Override
    public void handleAfterEachMethodExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
    }

    @Override
    public void handleAfterAllMethodExecutionException(ExtensionContext context, Throwable throwable)
            throws Throwable {
    }
}