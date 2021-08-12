package banknotes;

import org.junit.Test;

import static banknotes.Banknote.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BanknotesPickerTest {

    private final BanknotesPicker banknotesPicker = new BanknotesPicker();

    @Test
        public void should_pick_banknotes_when_all_types_of_banknotes_are_included() {
        // Arrange
        var amount = 8850;

        // Act
        var pickedBanknotes = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(pickedBanknotes)
            .containsOnly(Banknote.values());
    }

    @Test
    public void should_pick_banknotes_when_some_of_banknote_types_are_missing() {
        // Arrange
        var amount = 6200;

        // Act
        var pickedBanknotes = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(pickedBanknotes)
            .containsOnly(FIVE_THOUSAND, THOUSAND, TWO_HUNDRED);
    }

    @Test
    public void should_pick_banknotes_when_some_of_banknote_types_repeat() {
        // Arrange
        var amount = 4400;

        // Act
        var pickedBanknotes = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(pickedBanknotes)
            .containsOnly(TWO_THOUSAND, TWO_THOUSAND, TWO_HUNDRED, TWO_HUNDRED);
    }
}
