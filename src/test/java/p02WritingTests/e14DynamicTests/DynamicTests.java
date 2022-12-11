package p02WritingTests.e14DynamicTests;

import org.junit.jupiter.api.*;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DynamicTests {

    @TestFactory
    DynamicNode test1() {
        return DynamicContainer.dynamicContainer("Main Container",
                Stream.of(
                        DynamicContainer.dynamicContainer("SubContainer 1",
                                IntStream.range(1, 5)
                                        .mapToObj(i ->
                                                DynamicTest.dynamicTest(
                                                        "Test 1 [" + i + "]", () -> System.out.println(i)))
                        ),
                        DynamicContainer.dynamicContainer("SubContainer 2",
                                Stream.of(
                                        DynamicTest.dynamicTest("Test 2.1",
                                                () -> System.out.println("Test 2.1")),
                                        DynamicTest.dynamicTest("Test 2.2",
                                                () -> System.out.println("Test 2.2"))
                                )
                        ),
                        DynamicContainer.dynamicContainer("SubContainer 3",
                                Stream.concat(
                                        DynamicTest.stream(
                                                IntStream.iterate(1, i -> i * 2).limit(5).boxed(),
                                                i -> "[" + i + "]",
                                                System.out::println),
                                        DynamicTest.stream(
                                                IntStream.iterate(1, i -> i * 3).limit(5).
                                                        mapToObj(i -> Named.of("{" + i + "}", i)),
                                                System.out::println)
                                        )
                        )
                ));
    }
}
