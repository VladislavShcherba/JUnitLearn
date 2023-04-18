package p05ExtensionModel.e07PreDestroyCalBack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePreDestroyCallback;

@ExtendWith(MyPreDestroy.class)
class ATest {

    @Test
    void test1() {
        System.out.println("ATest.test1()");
    }

    @Test
    void test2() {
        System.out.println("ATest.test2()");
    }
}

class MyPreDestroy implements TestInstancePreDestroyCallback {

    @Override
    public void preDestroyTestInstance(ExtensionContext context) {
        System.out.println("MyPreDestroy.preDestroyTestInstance()");
    }
}