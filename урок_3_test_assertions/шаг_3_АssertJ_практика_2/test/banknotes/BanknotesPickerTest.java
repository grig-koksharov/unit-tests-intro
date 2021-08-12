package banknotes;

import org.junit.Test;

import static banknotes.Banknote.*;
import static org.assertj.core.api.Assertions.assertThat;

public class BanknotesPickerTest {

    private final BanknotesPicker banknotesPicker = new BanknotesPicker();

    @Test
        public void should_pick_banknotes_sorted() {
        // Arrange
        var amount = 8850;

        // Act
        var pickedBanknotes = banknotesPicker.pickBanknotesByAmount(amount);

        // Assert
        assertThat(pickedBanknotes)
            .containsExactly(Banknote.values());
    }
}
