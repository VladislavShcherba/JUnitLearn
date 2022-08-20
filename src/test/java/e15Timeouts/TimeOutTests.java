package e15Timeouts;

import main.Helper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class TimeOutTest {

    @BeforeAll
    @Timeout(1)
    static void beforeAll() {
        System.out.println("beforeAll(): " + Thread.currentThread());
    }

    @BeforeEach
    @Timeout(1)
    void beforeEach() {
        System.out.println("beforeEach(): " + Thread.currentThread());
    }

    @Test
    @Timeout(value = 500, unit = TimeUnit.MILLISECONDS, threadMode = Timeout.ThreadMode.SAME_THREAD)
    void test() {
        System.out.println("test(): " + Thread.currentThread());
        Helper.sleepFor(1000);
    }

}
