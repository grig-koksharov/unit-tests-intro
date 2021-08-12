package banknotes;

import org.junit.Test;

import java.util.List;

import static banknotes.Banknote.*;
import static org.assertj.core.api.Assertions.*;

public class BanknotesPickerTest {

    private final BanknotesPicker banknotesPicker = new BanknotesPicker();

    @Test
        public void should_throw_error_when_there_is_change() {
        // Arrange
        var amount = 12345;

        // Act / Assert
    }

    @Test
        public void should_throw_error_when_amount_is_negative() {
        // Arrange
        var amount = -100;

        // Act / Assert
    }

    @Test
        public void should_throw_error_when_amount_is_zero() {
        // Arrange
        var amount = 0;

        // Act / Assert
    }

    @Test
        public void should_not_throw_error_when_amount_is_positive_and_there_is_no_change() {
        // Arrange
        var amount = 100;

        // Act / Assert
    }
}
