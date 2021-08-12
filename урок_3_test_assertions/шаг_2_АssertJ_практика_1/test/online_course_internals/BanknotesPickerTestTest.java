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
        var testedMethodName = "should_pick_banknotes_when_all_types_of_banknotes_are_included";
        var expectedTestedValue = "такое колличество рублей, при котором возвращается каждая купюра по одной";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_all_types_of_banknotes_are_included);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isEqualTo(8850);
    }

    @Test
    public void should_pass_should_pick_banknotes_when_all_types_of_banknotes_are_included_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_when_all_types_of_banknotes_are_included";
        var expectedTestedValue = "такое колличество рублей, при котором возвращается каждая купюра по одной";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_all_types_of_banknotes_are_included);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isEqualTo(8850);
    }

    @Test
    public void should_fail_should_pick_banknotes_when_some_of_banknote_types_are_missing_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_when_some_of_banknote_types_are_missing";
        var expectedTestedValue = "такое колличество рублей, при котором некоторые виды купюры отсутствуют, но нет по несколько одинаковых купюр";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_some_of_banknote_types_are_missing);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatNoSameBanknotes(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatSomeBanknoteTypesAreMissing(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_pick_banknotes_when_some_of_banknote_types_are_missing_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_when_some_of_banknote_types_are_missing";
        var expectedTestedValue = "такое колличество рублей, при котором некоторые виды купюры отсутствуют";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_some_of_banknote_types_are_missing);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatNoSameBanknotes(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatSomeBanknoteTypesAreMissing(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_fail_should_pick_banknotes_when_some_of_banknote_types_repeat_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_when_some_of_banknote_types_repeat";
        var expectedTestedValue = "такое колличество рублей, при котором есть несколько одинаковых";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_some_of_banknote_types_repeat);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatThereAreSameBanknotes(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_pick_banknotes_when_some_of_banknote_types_repeat_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_pick_banknotes_when_some_of_banknote_types_repeat";
        var expectedTestedValue = "такое колличество рублей, при котором есть несколько одинаковых";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_pick_banknotes_when_some_of_banknote_types_repeat);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue))
            .satisfies(amount -> assertThatThereAreSameBanknotes(amount, testedMethodName, expectedTestedValue));
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

    private void assertThatSomeBanknoteTypesAreMissing(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(Set.copyOf(banknotes(amount)).size())
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isNotEqualTo(Banknote.values().length);
    }

    private void assertThatNoSameBanknotes(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(Set.copyOf(banknotes(amount)).size())
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isEqualTo(banknotes(amount).size());
    }

    private void assertThatThereAreSameBanknotes(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(Set.copyOf(banknotes(amount)).size())
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isNotEqualTo(banknotes(amount).size());
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