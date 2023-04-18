package p05ExtensionModel.e10TestLifecycleCallbacks;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstanceFactoryContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;
import org.junit.jupiter.api.extension.TestInstancePreConstructCallback;
import org.junit.jupiter.api.extension.TestInstancePreDestroyCallback;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

@ExtendWith(MyLifeCycleExtension1.class)
@ExtendWith(MyLifeCycleExtension2.class)
@ExtendWith(PreConstructPreDestroy.class)
@ExtendWith(PostProcessor.class)
@ExtendWith(MyTestWatcher.class)
class ATest {

    ATest() {
        System.out.println("ATest()");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("ATest.beforeAll()");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("ATest.beforeEach()");
    }

    @Test
    void test1() {
        System.out.println("ATest.test1()");
    }

    @Test
    void test2() {
        System.out.println("ATest.test2()");
        Assertions.fail();
    }

    @AfterEach
    void afterEach() {
        System.out.println("ATest.afterEach()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("ATest.afterAll()");
    }
}

class MyLifeCycleExtension1 implements BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

    public MyLifeCycleExtension1() {
        System.out.println("MyLifeCycleExtension1()");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.beforeAll()");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.beforeEach()");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.beforeTestExecution()");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.afterTestExecution()");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.afterEach()");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension1.afterAll()");
    }
}

class MyLifeCycleExtension2 implements BeforeAllCallback, BeforeEachCallback, BeforeTestExecutionCallback,
        AfterTestExecutionCallback, AfterEachCallback, AfterAllCallback {

    public MyLifeCycleExtension2() {
        System.out.println("MyLifeCycleExtension2()");
    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.beforeAll()");
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.beforeEach()");
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.beforeTestExecution()");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.afterTestExecution()");
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.afterEach()");
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        System.out.println("MyLifeCycleExtension2.afterAll()");
    }
}

class PreConstructPreDestroy implements TestInstancePreConstructCallback, TestInstancePreDestroyCallback {

    public PreConstructPreDestroy() {
        System.out.println("PreConstructPreDestroy()");
    }

    @Override
    public void preConstructTestInstance(TestInstanceFactoryContext factoryContext, ExtensionContext context) {
        System.out.println("PreConstructPreDestroy.preConstructTestInstance()");
    }

    @Override
    public void preDestroyTestInstance(ExtensionContext context) {
        System.out.println("PreConstructPreDestroy.preDestroyTestInstance()");
    }
}

class PostProcessor implements TestInstancePostProcessor {

    public PostProcessor() {
        System.out.println("PostProcessor()");
    }

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        System.out.println("PostProcessor.postProcessTestInstance()");
    }
}

class MyTestWatcher implements TestWatcher {

    public MyTestWatcher() {
        System.out.println("MyTestWatcher()");
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("MyTestWatcher.testDisabled()");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("MyTestWatcher.testSuccessful()");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("MyTestWatcher.testAborted()");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("MyTestWatcher.testFailed()");
    }
}