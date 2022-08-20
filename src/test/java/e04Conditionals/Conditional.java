package e04Conditionals;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class ConditionalTest {

    @Disabled("Disabled for demonstrative functions")
    @Test
    void disabled(){}

    @Test
    @EnabledOnOs(value=OS.WINDOWS, architectures = "amd64")
    void enabledOnWindows64(){}

    @Test
    @DisabledForJreRange(min= JRE.JAVA_10, max = JRE.JAVA_12)
    void notForAllJre(){}

    @Test
    @EnabledIfSystemProperty(named = "user.country", matches = "RU")
    void systemProperty(){}

    @Test
    @DisabledIfEnvironmentVariable(named = "NUMBER_OF_PROCESSORS", matches = "4")
    void environmentVariable(){}

    @Test
    @DisabledIf("returnsTrue")
    void customCondition() {}

    boolean returnsTrue() {
        return true;
    }

    @Test
    @EnabledIf("main.Helper#returnsFalse")
    void customConditionOutside(){}
}