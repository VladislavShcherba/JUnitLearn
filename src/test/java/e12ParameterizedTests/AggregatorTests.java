package e12ParameterizedTests;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.provider.CsvSource;

class ArgumentsAccessorTest {

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            #===========================================================
            NAME,                   SEX,    EQ_ID,      EQ_NAME
            #===========================================================
            'Mihail Shastak',       M,      asd2542,    ASUS-K551LN
            #-----------------------------------------------------------
            'Elizabeth Beneth',     F,      zqg8759,    'HP PAVILION'
            #-----------------------------------------------------------
            'Julia Robbins',        F,      rec8861,    'ASUS ZenBook'
            #===========================================================
            """)
    void test1(ArgumentsAccessor accessor) {
        String employeeName = accessor.getString(0);
        Sex employeeSex = Sex.of(accessor.getString(1));
        String eqId = accessor.getString(2);
        String eqName = accessor.getString(3);
        Equipment equipment = new Equipment(eqId, eqName);
        Employee employee = new Employee(employeeName, employeeSex, equipment);
        System.out.println(employee);
    }

    @ParameterizedTest
    @CsvSource(useHeadersInDisplayName = true, textBlock = """
            #===========================================================
            NAME,                   SEX,    EQ_ID,      EQ_NAME
            #===========================================================
            'Mihail Shastak',       M,      asd2542,    ASUS-K551LN
            #-----------------------------------------------------------
            'Elizabeth Beneth',     F,      zqg8759,    'HP PAVILION'
            #-----------------------------------------------------------
            'Julia Robbins',        F,      rec8861,    'ASUS ZenBook'
            #===========================================================
            """)
    void test2(@AggregateWith(EmployeeAggregator.class) Employee employee) {
        System.out.println(employee);
    }
}

class Employee {
    private String name;
    private Sex sex;
    private Equipment equipment;

    Employee(String name, Sex sex, Equipment equipment) {
        this.name = name;
        this.sex = sex;
        this.equipment = equipment;
    }

    @Override
    public String toString() {
        return name + " (" + sex + ") : " + equipment;
    }
}

enum Sex {
    MALE,
    FEMALE;

    static Sex of(String s) {
        return switch (s) {
            case "M" -> MALE;
            case "F" -> FEMALE;
            default -> null;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case MALE -> "M";
            case FEMALE -> "F";
        };
    }
}

class Equipment {
    private String id;
    private String name;

    Equipment(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " #" + id;
    }
}

class EmployeeAggregator implements ArgumentsAggregator {
    @Override
    public Employee aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) {
        String employeeName = accessor.getString(0);
        Sex employeeSex = Sex.of(accessor.getString(1));
        String eqId = accessor.getString(2);
        String eqName = accessor.getString(3);
        Equipment equipment = new Equipment(eqId, eqName);
        return new Employee(employeeName, employeeSex, equipment);
    }
}
