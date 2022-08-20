package e12ParameterizedTests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class ValueSourceTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test1(int arg) {
        System.out.println("The arg is " + arg);
    }
}

class NullEmptySourceTest {

    @ParameterizedTest
    @NullSource
    void test1(Calendar calendar) {
        Assertions.assertNull(calendar);
    }

    @ParameterizedTest
    @EmptySource
    void test2(List<String> list) {
        Assertions.assertTrue(list.isEmpty());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void test3(Calendar[][][] a3DCalendar) {
        System.out.println(Arrays.deepToString(a3DCalendar));
    }
}

class EnumSourceTest {

    @ParameterizedTest
    @EnumSource
    void test1(ChronoUnit chronoUnit) {
        System.out.println(chronoUnit);
    }

    @ParameterizedTest
    @EnumSource(value = ChronoUnit.class, mode = EnumSource.Mode.INCLUDE, names = {"NANOS", "MICROS", "MILLIS"})
    void test2(TemporalUnit temporalUnit) {
        System.out.println(temporalUnit);
    }
}

class MethodSourceTest {

    @ParameterizedTest
    @MethodSource("getInts")
    void test1(int i){
        System.out.println(i);
    }

    static IntStream getInts() {
        return IntStream.range(0, 3);
    }

    @ParameterizedTest
    @MethodSource("e12ParameterizedTests.ExternalMethod#getValuesForTest2")
    void test2(int i, double d, String s) {
        System.out.println(i + ", " + d + ", " + s);
    }

    @ParameterizedTest
    @MethodSource
    void test3(int i, double d, String s) {
        System.out.println(i + ", " + d + ", " + s);
    }

    static Stream<Arguments> test3() {
        return Stream.of(
                Arguments.of(1, 1.0d, "first"),
                Arguments.of(2, 2.0d, "second")
        );
    }

}

class ExternalMethod {
    static Collection<Object[]> getValuesForTest2() {
        return List.of(
                new Object[]{ 1, 1.0d, "first" },
                new Object[]{ 2, 2.0d, "second" }
        );
    }
}

class CsvSourceTest {

    @ParameterizedTest
    @CsvSource({
            "'The first', 1",
            "'The second', 2"
    })
    void test1(String str, int n) {
        System.out.println(str + ", " + n);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, nullValues = "NIL", delimiter = ';', textBlock = """
            #============================
            STRINGS;    INTS;   DOUBLES
            #============================
            First;      1;      1.1
            #----------------------------
            Second;     2;      2.2
            #----------------------------
            ;           3;      3.3
            #----------------------------
            NIL;        4;      4.4
            #============================
            """)
    void test2(String str, int n, double x) {
        System.out.println(str + ", " + n + ", " + x);
    }
}

class CsvFileSourceTest {

    @ParameterizedTest
    @CsvFileSource(resources = "example.csv", numLinesToSkip = 1)
    void test1(String str, int n) {
        System.out.println(str + ", " + n);
    }

}

class ArgumentsSourceTest {

    @ParameterizedTest
    @ArgumentsSource(StringProvider.class)
    void test1(String str) {
        System.out.println(str);
    }
}

class StringProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of("One", "Two").map(Arguments::of);
    }
}