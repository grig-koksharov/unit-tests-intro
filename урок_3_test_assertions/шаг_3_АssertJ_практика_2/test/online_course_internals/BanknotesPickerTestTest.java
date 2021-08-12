package online_course_internals;

import banknotes.Banknote;
import banknotes.BanknotesPicker;
import banknotes.BanknotesPickerTest;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Set;

import static online_course_internals.IncorrectTestMessages.*;
import static online_course_internals.TestMode.givenBuggyImplementation;
import static online_course_internals.TestMode.givenCorrectImplementation;
import static online_course_internals.TestedValues.cleanup;
import static online_course_internals.TestedValues.testedValues;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.InstanceOfAssertFactories.INTEGER;

public class BanknotesPickerTestTest {

    private final BanknotesPickerTest banknotesPickerTest = new BanknotesPickerTest();

    @Before
    public void before() {
        cleanup();
    }

    @After
    public void after() {
        givenCorrectImplementation();
    }

    @Test
    public void should_fail_should_pick_banknotes_when_all_types_of_banknotes_are_included_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_sorted";
        var expectedTestedValue = "колличество рублей, при котором возвращается больше 2 видов купюр";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_pick_banknotes_sorted);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatThereAreMoreThan2TypesOfBanknotes(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_pick_banknotes_when_all_types_of_banknotes_are_included_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_sorted";
        var expectedTestedValue = "колличество рублей, при котором возвращается больше 2 видов купюр";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_pick_banknotes_sorted);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatThereAreMoreThan2TypesOfBanknotes(amount, testedMethodName, expectedTestedValue));
    }

    private AbstractIntegerAssert<?> assertThatRequestedAmount(String testedMethodName, String expectedTestedValue) {
        assertThat(testedValues())
            .overridingErrorMessage(notSingleTestedValue(testedMethodName))
            .hasSize(1);
        return assertThat(testedValues()).first().asInstanceOf(INTEGER)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue));
    }

    private void assertThatTestFails(String testedMethodName, ThrowingCallable test) {
        assertThat(catchThrowable(test))
            .overridingErrorMessage(noFailWhenBuggyImplementation(testedMethodName))
            .isInstanceOf(AssertionError.class);
    }

    private void assertThatTestDoesNotFail(String testedMethodName, ThrowingCallable test) {
        assertThatCode(test)
            .overridingErrorMessage(failWhenCorrectImplementation(testedMethodName))
            .doesNotThrowAnyException();
    }

    private void assertThatThereAreMoreThan2TypesOfBanknotes(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(Set.copyOf(banknotes(amount)).size())
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isGreaterThan(2);
    }

    private void assertThatNoChange(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(amount % 50 == 0)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isTrue();
    }

    private Collection<Banknote> banknotes(int amount) {
        givenCorrectImplementation();
        return new BanknotesPicker().pickBanknotesByAmount(amount);
    }
}