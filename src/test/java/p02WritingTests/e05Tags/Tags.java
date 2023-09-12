package p02WritingTests.e05Tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("Smoke")
@interface Smoke {
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("Regression")
@interface Regression {
}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("Online")
@interface Online {
}

@Smoke
class SmokeTest {

    @Test
    void smokeOffline() {
        System.out.println("smokeOffline()");
    }

    @Online
    @Test
    void smokeOnline() {
        System.out.println("smokeOnline()");
    }
}

@Regression
class RegressionTest {

    @Test
    void regressionOffline() {
        System.out.println("regressionOffline()");
    }

    @Online
    @Test
    void regressionOnLine() {
        System.out.println("regressionOnline()");
    }
}

class OtherTest {

    @Test
    void otherTest() {
        System.out.println("otherTest()");
    }
}
