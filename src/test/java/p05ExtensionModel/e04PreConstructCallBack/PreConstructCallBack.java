package p05ExtensionModel.e04PreConstructCallBack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstancePreConstructCallback;

@ExtendWith(MyPreConstructExtension.class)
class PreConstructTest {

    @Test
    void test1() {
        System.out.println("PreConstructTest.test1()");
    }

    @Test
    @ExtendWith(OtherPreConstructExtension.class)
    void test2() {
        System.out.println("PreConstructTest.test2()");
    }
}

class MyPreConstructExtension implements TestInstancePreConstructCallback {

    @Override
    public void preConstructTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext context) {
        System.out.println("Inside MyPreConstructExtension.preConstructTestInstance()");
    }
}

class OtherPreConstructExtension implements TestInstancePreConstructCallback {

    @Override
    public void preConstructTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext context) {
        System.out.println("Inside OtherPreConstructExtension.preConstructTestInstance()");
    }
}