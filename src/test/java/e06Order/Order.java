package e06Order;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrderAnnotationTest {

    @Order(3)
    @Test
    void test1(){}

    @Order(2)
    @Test
    void test2(){}

    @Order(1)
    @Test
    void test3(){}

}

class WithoutOrderingTest {

    @Test
    void test1(){}

    @Test
    void test2(){}

    @Test
    void test3(){}
}
