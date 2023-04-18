package p05ExtensionModel.e12InterceptingInvocations;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.lang.reflect.Method;

@ExtendWith(SimpleInvocationInterceptor.class)
class SimpleTest {
    @Test
    void test(TestInfo testInfo) {
        System.out.println("SimpleTest.test()");
    }
}

class SimpleInvocationInterceptor implements InvocationInterceptor {
    @Override
    public void interceptTestMethod(Invocation<Void> invocation,
                                    ReflectiveInvocationContext<Method> invocationContext,
                                    ExtensionContext extensionContext) throws Throwable {
        System.out.println("SimpleInvocationInterceptor: Intercepted!");
        System.out.println(invocationContext.getArguments());
        System.out.println(invocationContext.getExecutable());
        System.out.println(invocationContext.getTarget());
        System.out.println(invocationContext.getTargetClass());
        invocation.proceed();
    }
}

@ExtendWith(ParameterizedInvocationInterceptor.class)
class PTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("PTest.beforeAll()");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("PTest.beforeEach()");
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 20, 300})
    void test(int i) {
        System.out.println("PTest.test(" + i + ")");
    }

    @AfterEach
    void afterEach() {
        System.out.println("PTest.afterEach()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("PTest.afterAll()");
    }
}

class ParameterizedInvocationInterceptor implements InvocationInterceptor {

    private void print(ReflectiveInvocationContext<Method> invocationContext) {
        System.out.println(invocationContext.getArguments());
        System.out.println(invocationContext.getExecutable());
        System.out.println(invocationContext.getTarget());
        System.out.println(invocationContext.getTargetClass());
    }

    @Override
    public void interceptBeforeAllMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        System.out.println("interceptBeforeAllMethod()");
        print(invocationContext);
        invocation.proceed();
    }

    @Override
    public void interceptBeforeEachMethod(Invocation<Void> invocation,
                                          ReflectiveInvocationContext<Method> invocationContext,
                                          ExtensionContext extensionContext) throws Throwable {
        System.out.println("interceptBeforeEachMethod()");
        print(invocationContext);
        invocation.proceed();
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation,
                                            ReflectiveInvocationContext<Method> invocationContext,
                                            ExtensionContext extensionContext) throws Throwable {
        System.out.println("interceptTestTemplateMethod()");
        print(invocationContext);
        invocation.proceed();
    }

    @Override
    public void interceptAfterEachMethod(Invocation<Void> invocation,
                                         ReflectiveInvocationContext<Method> invocationContext,
                                         ExtensionContext extensionContext) throws Throwable {
        System.out.println("interceptAfterEachMethod()");
        print(invocationContext);
        invocation.proceed();
    }

    @Override
    public void interceptAfterAllMethod(Invocation<Void> invocation,
                                        ReflectiveInvocationContext<Method> invocationContext,
                                        ExtensionContext extensionContext) throws Throwable {
        System.out.println("interceptAfterAllMethod()");
        print(invocationContext);
        invocation.proceed();
    }
}