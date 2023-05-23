package p04RunningTests.e02Logging.jul.page;

import java.util.logging.Logger;

public class B1Page extends BPage {

    private static final Logger logger = Logger.getLogger(B1Page.class.getName());

    @Override
    public void f() {
        logger.entering(B1Page.class.getName(), "f()");
    }

    public void g() {
        logger.entering(B1Page.class.getName(), "g()");
    }
}
