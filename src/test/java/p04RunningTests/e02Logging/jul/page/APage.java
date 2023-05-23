package p04RunningTests.e02Logging.jul.page;

import java.util.logging.Logger;

public class APage {

    private static final Logger logger = Logger.getLogger(APage.class.getName());

    public void f() {
        logger.entering(APage.class.getName(), "f()");
    }
}
