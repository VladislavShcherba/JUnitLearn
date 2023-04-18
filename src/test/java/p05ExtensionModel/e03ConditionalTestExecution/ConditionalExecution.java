package p05ExtensionModel.e03ConditionalTestExecution;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ConditionEvaluationResult;
import org.junit.jupiter.api.extension.ExecutionCondition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Random;

@ExtendWith(RandomDisabled.class)
class ConditionalExecutionTest {

    @Test
    void test1() {
        System.out.println("ConditionalExecutionTest.test1()");
    }

    @Test
    void test2() {
        System.out.println("ConditionalExecutionTest.test2()");
    }
}

class RandomDisabled implements ExecutionCondition {

    @Override
    public ConditionEvaluationResult evaluateExecutionCondition(ExtensionContext context) {
        System.out.println("Inside RandomDisabled.evaluateExecutionCondition()");

        System.out.println("extensionContext.getElement() : " + context.getElement().get());

        boolean isDisabled = new Random().nextBoolean();
        System.out.println("isDisabled : " + isDisabled);

        if(isDisabled) {
            return ConditionEvaluationResult.disabled("Reason: random returns true");
        }
        return ConditionEvaluationResult.enabled("Reason: random returns false");
    }
}