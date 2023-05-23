package p04RunningTests.e02Logging.jul;

import org.junit.jupiter.api.Test;
import p04RunningTests.e02Logging.jul.page.APage;
import p04RunningTests.e02Logging.jul.page.B1Page;
import p04RunningTests.e02Logging.jul.page.BPage;

public class ATest extends BaseTest {

    @Test
    public void test() {
        new APage().f();
        new BPage().f();
        new B1Page().f();
        new B1Page().g();
    }

}
