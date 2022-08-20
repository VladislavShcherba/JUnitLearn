package e16ParallelExecution;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.api.parallel.ResourceAccessMode;
import org.junit.jupiter.api.parallel.ResourceLock;

import static main.Helper.*;

@Execution(ExecutionMode.CONCURRENT)
class ParallelFirstTest {

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test1() {
        System.out.println("ParallelFirstTest.test1() " + methodInvocationInfo());
        sleepFor(1000);
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test2() {
        System.out.println("ParallelFirstTest.test2() " + methodInvocationInfo());
        sleepFor(2000);
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test3() {
        System.out.println("ParallelFirstTest.test3() " + methodInvocationInfo());
        sleepFor(3000);
    }
}

@Execution(ExecutionMode.CONCURRENT)
class ParallelSecondTest {

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test1() {
        System.out.println("ParallelSecondTest.test1() " + methodInvocationInfo());
        sleepFor(1000);
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test2() {
        System.out.println("ParallelSecondTest.test2() " + methodInvocationInfo());
        sleepFor(2000);
    }

    @Test
    @Execution(ExecutionMode.SAME_THREAD)
    void test3() {
        System.out.println("ParallelSecondTest.test3() " + methodInvocationInfo());
        sleepFor(3000);
    }
}

class CommonResource {

    private static String common;

    static String read() {
        return common;
    }

    static void write(String newValue) {
        common = newValue;
    }
}

@Execution(ExecutionMode.CONCURRENT)
class ParallelThirdTest {

    @Test
    @ResourceLock(value = "common resource 1", mode = ResourceAccessMode.READ_WRITE)
    void test1() {
        System.out.println("ParallelThirdTest.test1() " + methodInvocationInfo());
        String str = "test1";
        CommonResource.write(str);
        Thread.yield();
        Assertions.assertEquals(str, CommonResource.read());
    }

    @Test
    @ResourceLock(value = "common resource 1", mode = ResourceAccessMode.READ_WRITE)
    void test2() {
        System.out.println("ParallelThirdTest.test2() " + methodInvocationInfo());
        String str = "test2";
        CommonResource.write(str);
        Thread.yield();
        Assertions.assertEquals(str, CommonResource.read());
    }

    @Test
    @ResourceLock(value = "common resource 1", mode = ResourceAccessMode.READ)
    void test3() {
        System.out.println("ParallelThirdTest.test3() " + methodInvocationInfo() + " r: " + CommonResource.read());
        sleepFor(1000);
    }
    @Test
    @ResourceLock(value = "common resource 1", mode = ResourceAccessMode.READ)
    void test4() {
        System.out.println("ParallelThirdTest.test4() " + methodInvocationInfo() + " r: " + CommonResource.read());
        sleepFor(2000);
    }
    @Test
    @ResourceLock(value = "common resource 1", mode = ResourceAccessMode.READ)
    void test5() {
        System.out.println("ParallelThirdTest.test5() " + methodInvocationInfo() + " r: " + CommonResource.read());
        sleepFor(3000);
    }
}
