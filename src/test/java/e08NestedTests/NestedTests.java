package e08NestedTests;

import org.junit.jupiter.api.*;

class NestedTest {

    // @BeforeAll and @AfterAll should be static unless Lifecycle.PER_CLASS is used
    @BeforeAll
    static void beforeAll() {
        System.out.println("NestedTest.beforeAll()");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("NestedTest.afterAll()");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("NestedTest.beforeEach()");
    }

    @AfterEach
    void afterEach() {
        System.out.println("NestedTest.afterEach()");
    }

    @Test
    void test1() {
        System.out.println("NestedTest.test1()");
    }

    @Test
    void test2() {
        System.out.println("NestedTest.test2()");
    }

    @Nested
    class InnerOneTest {
        @BeforeAll
        static void beforeAll() {
            System.out.println("NestedTest.InnerOne.beforeAll()");
        }

        @AfterAll
        static void afterAll() {
            System.out.println("NestedTest.InnerOne.afterAll()");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("NestedTest.InnerOne.beforeEach()");
        }

        @AfterEach
        void afterEach() {
            System.out.println("NestedTest.InnerOne.afterEach()");
        }

        @Test
        void test1() {
            System.out.println("NestedTest.InnerOne.test1()");
        }

        @Test
        void test2() {
            System.out.println("NestedTest.InnerOne.test2()");
        }
    }
}
