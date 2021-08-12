package simple;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SimpleTest {

    @Test
    public void should_get_4_when_adding_2_to_2() {
        // Arrange
        var first2 = 2;
        var second2 = 2;

        // Act
        var sumResult = first2 + second2;

        // Assert
        assertThat(sumResult).isEqualTo(4);
    }
}