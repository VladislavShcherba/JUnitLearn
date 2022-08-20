package e05Tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("ClassTag")
class TaggedTest {

    @Test
    @Tag("MethodTag")
    void test(){}
}
