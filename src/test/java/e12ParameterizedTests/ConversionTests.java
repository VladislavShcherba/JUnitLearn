package e12ParameterizedTests;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

class WideningConversionTest {

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void test1(double d) {
        System.out.println(d);
    }
}

class ImplicitConversionTest {

    //String -> LocalDate
    @ParameterizedTest
    @ValueSource(strings = {"2020-01-01"})
    void test1(LocalDate date) {
        Assertions.assertEquals(LocalDate.of(2020, 1, 1), date);
    }

    //String -> enum
    @ParameterizedTest
    @ValueSource(strings = {"MILLIS"})
    void test1(ChronoUnit unit) {
        Assertions.assertEquals(ChronoUnit.MILLIS, unit);
    }

    //...
}

class FallbackConversionTest {

    @ParameterizedTest
    @ValueSource(strings = {"Nick", "David"})
    void test1(Person person) {
        System.out.println(person.getName());
    }
    @ParameterizedTest
    @ValueSource(strings = {"BMW", "LADA"})
    void test2(Car car) {
        System.out.println(car.getBrand());
    }
}

class Person {

    private String name;

    Person(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}

class Car {

    private String brand;

    private Car(String brand) {
        this.brand = brand;
    }

    String getBrand() {
        return brand;
    }

    static Car ofBrand(String brand) {
        return new Car(brand);
    }
}

class ExplicitConverterTest {

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World"})
    void test1(@ConvertWith(CountVowelsConverter.class) int i) {
        System.out.println("Vowels: " + i);
    }
}

class CountVowelsConverter extends TypedArgumentConverter<String, Integer> {

    public CountVowelsConverter() {
        super(String.class, Integer.class);
    }

    @Override
    protected Integer convert(String source) throws ArgumentConversionException {
        return (int) Arrays.stream(source.toLowerCase().split(StringUtils.EMPTY))
                .filter(s->s.matches("^[aeiou]$"))
                .count();
    }
}
