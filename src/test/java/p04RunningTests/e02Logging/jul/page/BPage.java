package p04RunningTests.e02Logging.jul.page;

import java.util.logging.Logger;

public class BPage {

    private static final Logger logger = Logger.getLogger(BPage.class.getName());

    public void f() {
        logger.entering(BPage.class.getName(), "f()");
    }
}
