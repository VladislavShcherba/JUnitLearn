package p00UseCases.ParameterizedStateBeforeDriver;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.stream.Stream;

//Solution 1:
//pros - no need to add startTest(state) in each test method
//cons - beforeEach methods in tests are 'too much before' == even before driver initialization
@ExtendWith(Solution1BaseTest.StateRestorerInterceptor.class)
class Solution1BaseTest {

    @BeforeEach
    public void beforeEach() {
        System.out.println("Solution1BaseTest.beforeEach()");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("Solution1BaseTest.afterEach()");
        new Driver().close(); //stop driver
    }

    static class StateRestorerInterceptor implements InvocationInterceptor {
        @Override
        public void interceptTestTemplateMethod(Invocation<Void> invocation,
                                                ReflectiveInvocationContext<Method> invocationContext,
                                                ExtensionContext extensionContext) throws Throwable {
            State state = (State) invocationContext.getArguments().get(0);
            System.out.println(state); // restore state
            new Driver().setUp(); // start driver
            invocation.proceed();
        }
    }
}

class Solution1Test extends Solution1BaseTest {

    @ParameterizedTest
    @MethodSource
    public void test(State state) {
        System.out.println("Solution1Test.simpleTest()");
    }

    public static Stream<State> test() {
        return Stream.of(new State("state A"), new State("state B"));
    }
}
