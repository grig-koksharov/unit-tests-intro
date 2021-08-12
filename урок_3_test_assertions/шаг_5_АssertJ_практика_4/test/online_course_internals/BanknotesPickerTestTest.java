package online_course_internals;

import banknotes.BanknotesPickerTest;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.String.format;
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
    public void should_fail_should_throw_error_when_there_is_change_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_there_is_change";
        var expectedTestedValue = "колличество рублей, чтобы не нашлось купюр без сдачи";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_throw_error_when_there_is_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatThereIsChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_throw_error_when_there_is_change_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_there_is_change";
        var expectedTestedValue = "колличество рублей, чтобы не нашлось купюр без сдачи";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_throw_error_when_there_is_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatThereIsChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_fail_should_throw_error_when_amount_is_negative_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_amount_is_negative";
        var expectedTestedValue = "колличество рублей меньше нуля";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_throw_error_when_amount_is_negative);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isNegative()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_throw_error_when_amount_is_negative_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_amount_is_negative";
        var expectedTestedValue = "колличество рублей меньше нуля";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_throw_error_when_amount_is_negative);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isNegative()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_fail_should_throw_error_when_amount_is_zero_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_amount_is_zero";
        var expectedTestedValue = "колличество рублей равное нулю";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_throw_error_when_amount_is_zero);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isZero()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_throw_error_when_amount_is_zero_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_throw_error_when_amount_is_zero";
        var expectedTestedValue = "колличество рублей равное нулю";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_throw_error_when_amount_is_zero);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isZero()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_fail_should_not_throw_error_when_amount_is_positive_and_there_is_no_change_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_not_throw_error_when_amount_is_positive_and_there_is_no_change";
        var expectedTestedValue = "колличество рублей без сдачи";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_not_throw_error_when_amount_is_positive_and_there_is_no_change)
            .hasMessageContaining("Expecting code not to raise a throwable but caught");
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_not_throw_error_when_amount_is_positive_and_there_is_no_change_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_not_throw_error_when_amount_is_positive_and_there_is_no_change";
        var expectedTestedValue = "колличество рублей без сдачи";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_not_throw_error_when_amount_is_positive_and_there_is_no_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    private AbstractIntegerAssert<?> assertThatRequestedAmount(String testedMethodName, String expectedTestedValue) {
        assertThat(testedValues())
            .overridingErrorMessage(notSingleTestedValue(testedMethodName))
            .hasSize(1);
        return assertThat(testedValues()).first().asInstanceOf(INTEGER)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue));
    }

    private AbstractThrowableAssert<?, ? extends Throwable> assertThatTestFails(String testedMethodName, ThrowingCallable test) {
        return assertThat(catchThrowable(test))
            .overridingErrorMessage(noFailWhenBuggyImplementation(testedMethodName))
            .isInstanceOf(AssertionError.class);
    }

    private void assertThatTestDoesNotFail(String testedMethodName, ThrowingCallable test) {
        assertThatCode(test)
            .overridingErrorMessage(failWhenCorrectImplementation(testedMethodName))
            .doesNotThrowAnyException();
    }

    private void assertThatNoChange(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(amount % 50 == 0)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isTrue();
    }

    private void assertThatThereIsChange(Integer amount, String testedMethodName, String expectedTestedValue) {
        assertThat(amount % 50 != 0)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, expectedTestedValue))
            .isTrue();
    }
}