package p05ExtensionModel.e06PostProcessing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

@ExtendWith(APostProcessor.class)
class ATest {

    private int i;

    ATest increment() {
        i++;
        return this;
    }

    @Test
    void test1() {
        System.out.println("ATest.test1() : i = " + i);
    }
}

class APostProcessor implements TestInstancePostProcessor {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) {
        if(testInstance instanceof ATest) {
            ATest aTest = (ATest) testInstance;
            aTest.increment().increment().increment();
        }
    }
}