package simple;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleTest {

    @Test
    public void дважды_два_четыре() {
        assertThat(2 * 2)
            .isEqualTo(4);
    }
}