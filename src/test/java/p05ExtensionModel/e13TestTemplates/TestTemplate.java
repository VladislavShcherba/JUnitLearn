package p05ExtensionModel.e13TestTemplates;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

class TestTemplateTest {
    @TestTemplate
    @ExtendWith(ContextProvider.class)
    @DisplayName("Common @DisplayName")
    void test(int param) {
        System.out.println("TestTemplateTest.test(param: " + param + ")");
    }
}

class ContextProvider implements TestTemplateInvocationContextProvider {
    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        Optional<Method> testMethod = context.getTestMethod();
        if(testMethod.isPresent()) {
            Type[] testMethodsParameters = testMethod.get().getGenericParameterTypes();
            return Arrays.equals(testMethodsParameters, new Type[]{int.class});
        }
        return false;
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        return Stream.of(invocationContext(22), invocationContext(333));
    }

    private TestTemplateInvocationContext invocationContext(int parameter) {
        return new TestTemplateInvocationContext() {
            @Override
            public String getDisplayName(int invocationIndex) {
                return "Test template with parameter " + parameter;
            }

            @Override
            public List<Extension> getAdditionalExtensions() {
                return Collections.singletonList(new ParameterResolver() {
                    @Override
                    public boolean supportsParameter(ParameterContext parameterContext,
                                                     ExtensionContext extensionContext) {
                        return true;
                    }

                    @Override
                    public Object resolveParameter(ParameterContext parameterContext,
                                                   ExtensionContext extensionContext) {
                        return parameter;
                    }
                });
            }
        };
    }
}