package p00UseCases.ParameterizedStateBeforeDriver;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.util.List;
import java.util.stream.Stream;

//Solution 2:
//pros - no need to add startTest(state) in each test method
//pros - beforeEach methods can be used after state restore and driver setup are done
//cons - need to declare states provider class for each test method


class StateParameterResolver implements ParameterResolver {

    private final State state;

    public StateParameterResolver(State state) {
        this.state = state;
    }

    @Override
    public boolean supportsParameter(ParameterContext parameterContext,
                                     ExtensionContext extensionContext) {
        return parameterContext.getParameter().getType().equals(State.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext,
                                   ExtensionContext extensionContext) {
        return state;
    }
}

class BeforeEachHook implements BeforeEachCallback {

    private final State state;

    public BeforeEachHook(State state) {
        this.state = state;
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        state.restore(); // restore state
        new Driver().setUp(); // start driver
    }
}

class AfterEachHook implements AfterEachCallback {

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        new Driver().close(); //stop driver
    }
}

class StatesProvider implements TestTemplateInvocationContextProvider {

    private final String executionsName;
    private final Stream<State> states;

    public StatesProvider(String executionsName, Stream<State> states) {
        this.executionsName = executionsName;
        this.states = states;
    }

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        return true;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return states.map(this::invocationContext);
    }

    private TestTemplateInvocationContext invocationContext(State state) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return executionsName + " for " + state + " of iteration " + invocationIndex;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return List.of(
                        new StateParameterResolver(state),
                        new BeforeEachHook(state),
                        new AfterEachHook()
                        );
            }
        };
    }
}

class ATestStatesProvider extends StatesProvider {
    public ATestStatesProvider() {
        super("ATest execution",
                Stream.of(new State("AState"), new State("BState")));
    }
}

class ATest {

    @BeforeEach
    public void beforeEach() {
        System.out.println("ATest.beforeEach()");
    }

    @DisplayName("Common name for executions")
    @TestTemplate
    @ExtendWith(ATestStatesProvider.class)
    public void test(State state) {
        System.out.println("test(State:" + state + ")");
    }

    @AfterEach
    public void afterEach() {
        System.out.println("ATest.afterEach()");
    }
}