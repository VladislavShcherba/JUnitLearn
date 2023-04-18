package p05ExtensionModel.e05TestInstanceFactories;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactory;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;

import java.util.Random;

class ATest {

    private String str;

    @Test
    void test1() {
        System.out.println("str = " + str);
    }
}

class BTest {

    private String str;

    private BTest(){}

    private BTest(String str) {
        this.str = str;
    }

    @Test
    void test1() {
        System.out.println("str = " + str);
    }
}

@ExtendWith(CTestFactory.class)
class CTest {

    private String str;

    private CTest(){}

    CTest(String str) {
        this.str = str;
    }

    @Test
    void test1() {
        System.out.println("str = " + str);
    }
}

class CTestFactory implements TestInstanceFactory {
    @Override
    public Object createTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext extensionContext) {
        return new CTest("Hello!");
    }
}

@ExtendWith(DTestFactory.class)
class DTest {

    protected String str;

    private DTest(){}

    public DTest(String str) {
        this.str = str;
    }

    @Test
    void test1() {
        System.out.println("DTest.test1() : str = " + str);
    }
}

class DTestFactory implements TestInstanceFactory {
    @Override
    public Object createTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext extensionContext) {
        System.out.println("Inside DTestFactory.createTestInstance()");
        boolean randBool = new Random().nextBoolean();
        System.out.println("randBool = " + randBool);

        if(randBool) {
            return new DTest("Extended!") {
                @Test
                void test1() {
                    System.out.println("override DTest.test1() : str = " + str);
                }
            };
        }
        return new DTest("Simple!");
    }
}