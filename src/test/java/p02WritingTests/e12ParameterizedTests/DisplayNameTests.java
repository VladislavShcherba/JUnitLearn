package p02WritingTests.e12ParameterizedTests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.stream.Stream;

public class DisplayNameTests {

    @DisplayName("Summary name")
    @ParameterizedTest(name = "*{index} -> [{0}/{1}]")
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            #=======================
            STRINGS,            INTS
            #=======================
            Hello,              1
            #-----------------------
            World,              2
            #=======================
            """)
    void test1(String str, int i) {
        System.out.println(str + ", " + i);
    }

    @DisplayName("Summary:")
    @ParameterizedTest(name = ">{index} ==> [{0} : {1}]")
    @ArgumentsSource(MyProvider.class)
    void test2(ArgumentsAccessor accessor) {
        System.out.println(accessor.getString(0) + " : " + accessor.getInteger(1));
    }
}

class MyProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(
                        Named.named("First person name", "Vladimir"),
                        Named.named("First person mark", 10)
                ),
                Arguments.of(
                        Named.named("Second person name", "Maria"),
                        Named.named("Second person mark", 9)
                )
        );
    }
}
