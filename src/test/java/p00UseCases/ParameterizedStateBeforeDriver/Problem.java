package p00UseCases.ParameterizedStateBeforeDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class State {
    private final String name;

    public State(String name) {
        this.name = name;
    }

    public void restore() {
        System.out.println("State{"+ name + "}.restore()");
    }

    @Override
    public String toString() {
        return "State{"+ name + "}";
    }
}

class Driver {
    public void setUp() {
        System.out.println("Driver.setUp()");
    }

    public void close() {
        System.out.println("Driver.close()");
    }
}

class SimpleBaseTest {

    // state should be restored before starting driver,
    // but it comes as parameter in parameterized test,
    // so @BeforeEach can not be used
    public void startTest(State state) {
        state.restore(); // restore state
        new Driver().setUp(); // start driver
    }

    @AfterEach
    public void stopTest() {
        new Driver().close(); //stop driver
    }
}

class SimpleTest extends SimpleBaseTest {

    @ParameterizedTest
    @MethodSource
    public void simpleTest(State state) {
        System.out.println("SimpleTest.simpleTest()");
        startTest(state); // have to start each test method with this method call
    }

    public static Stream<State> simpleTest() {
        return Stream.of(new State("state A"), new State("state B"));
    }
}
