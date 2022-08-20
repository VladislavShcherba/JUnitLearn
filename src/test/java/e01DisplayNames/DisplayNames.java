package e01DisplayNames;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("This is DisplayNameSimpleTest class")
class DisplayNameSimpleTest {

    @Test
    void test1() {
    }

    @Test
    @DisplayName("This is test2 method of DisplayNameSimpleTest class")
    void test2() {

    }

    @ParameterizedTest(name = "The test is parameterized: {0}")
    @ValueSource(strings = {"string1", "string2"})
    @DisplayName("This is test3 method of DisplayNameSimpleTest class")
    void test3(String argument) {

    }
}

@DisplayNameGeneration(DisplayNameGenerator.IndicativeSentences.class)
@DisplayName("This is DisplayNameWithGeneratorTest class")
class DisplayNameWithGeneratorTest {

    @Test
    void test1() {

    }

    @Test
    @DisplayName("This is test2 method of DisplayNameWithGeneratorTest class")
    void test2() {

    }

    @ParameterizedTest(name = "The test is parameterized: {0}")
    @ValueSource(strings = {"string1", "string2"})
    @DisplayName("This is test3 method of DisplayNameWithGeneratorTest class")
    void test3(String argument) {

    }
}

@IndicativeSentencesGeneration(separator = "~>", generator = DisplayNameGenerator.Simple.class)
@DisplayName("This is DisplayNameWithIndicativeGeneratorTest class")
class DisplayNameWithIndicativeGeneratorTest {

    @Test
    void test1() {

    }

    @Test
    @DisplayName("This is test2 method of DisplayNameWithIndicativeGeneratorTest class")
    void test2() {

    }

    @ParameterizedTest(name = "The test is parameterized: {0}")
    @ValueSource(strings = {"string1", "string2"})
    @DisplayName("This is test3 method of DisplayNameWithIndicativeGeneratorTest class")
    void test3(String argument) {

    }
}


