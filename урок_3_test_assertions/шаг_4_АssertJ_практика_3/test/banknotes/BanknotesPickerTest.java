package banknotes;

import org.junit.Test;

import java.util.List;

import static banknotes.Banknote.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BanknotesPickerTest {

    private final BanknotesPicker banknotesPicker = new BanknotesPicker();

    @Test
        public void should_return_empty_result_when_cannot_pick_banknotes_with_no_change() {
        // Arrange
        var amount = 12345;

        // Act
        var result = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(result)
            .isEmpty();
    }

    @Test
        public void should_return_banknotes_when_can_pick_banknotes_with_no_change() {
        // Arrange
        var amount = 100;

        // Act
        var result = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(result).get().asList()
            .containsOnly(HUNDRED);
    }
}
