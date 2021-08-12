package card;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CardTest {

    @Test
    public void should_have_positive_balance_when_balance_is_greater_than_0() {
        // Arrange
        var card = ;

        // Act
        var isBalancePositive = card.hasPositiveBalance();

        // Assert
        assertThat(isBalancePositive).isTrue();
    }

    @Test
    public void should_not_have_positive_balance_when_balance_is_less_than_0() {
        // Arrange
        var card = new Card(-10);

        // Act
        var isBalancePositive = ;

        // Assert
        assertThat(isBalancePositive).isFalse();
    }

    @Test
    public void should_not_have_positive_balance_when_balance_is_equal_to_0() {
        // Arrange
        var card = new Card(0);

        // Act
        var isBalancePositive = card.hasPositiveBalance();

        // Assert
        assertThat(isBalancePositive).isFalse();
    }
}