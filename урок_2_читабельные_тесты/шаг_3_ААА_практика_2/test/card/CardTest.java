package card;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    @Test
    public void should_have_negative_balance_when_balance_is_less_than_0() {
        // Arrange
        var card = new Card(-10);

        // Act
        var isBalanceNegative = card.hasNegativeBalance();

        // Assert
        assertThat(isBalanceNegative).isTrue();
    }

    @Test
    public void should_not_have_negative_balance_when_balance_is_greater_than_0() {
        // Arrange
        var card = new Card(10);

        // Act
        var isBalanceNegative = card.hasNegativeBalance();

        // Assert
        assertThat(isBalanceNegative).isFalse();
    }

    @Test
    public void should_not_have_negative_balance_when_balance_is_equal_to_0() {
        // Arrange
        var card = new Card(0);

        // Act
        var isBalanceNegative = card.hasNegativeBalance();

        // Assert
        assertThat(isBalanceNegative).isFalse();
    }
}