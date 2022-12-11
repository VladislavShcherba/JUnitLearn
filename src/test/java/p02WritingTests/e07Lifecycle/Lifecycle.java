package p02WritingTests.e07Lifecycle;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
class PerMethodTest {

    static int counter;

    PerMethodTest() {
        counter++;
    }

    @Test
    @Order(1)
    void test1(){}

    @Disabled
    @Test
    @Order(2)
    void test2(){}

    @Test
    @Order(3)
    void test3() {
        System.out.println("PerMethodTest.test3.counter: " + counter);
    }
}

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PerClassTest {

    static int counter;

    PerClassTest() {
        counter++;
    }

    @Test
    @Order(1)
    void test1(){}

    @Disabled
    @Test
    @Order(2)
    void test2(){}

    @Test
    @Order(3)
    void test3() {
        System.out.println("PerClassTest.test3.counter: " + counter);
    }
}

// Default lifecycle is PER_METHOD, but can be changed, for example, in junit-platform.properties file
class StandardTest {

    static int counter;

    StandardTest() {
        counter++;
    }

    @Test
    @Order(1)
    void test1(){}

    @Disabled
    @Test
    @Order(2)
    void test2(){}

    @Test
    @Order(3)
    void test3() {
        System.out.println("StandardTest.test3.counter: " + counter);
    }
}