package card;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    @Test
        public void should_have_zero_balance_when_balance_is_equal_to_0() {
        // Arrange
        var card = new Card(0);

        // Act
        var isBalanceNegative = card.hasZeroBalance();

        // Assert
        assertThat(isBalanceNegative).isTrue();
    }

    @Test
    public void should_not_have_zero_balance_when_balance_is_not_equal_to_0() {
        // Arrange
        var card = new Card(1);

        // Act
        var isBalanceNegative = card.hasZeroBalance();

        // Assert
        assertThat(isBalanceNegative).isFalse();
    }
}