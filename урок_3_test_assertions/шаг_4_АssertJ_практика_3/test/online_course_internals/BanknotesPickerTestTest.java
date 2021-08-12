package online_course_internals;

import banknotes.BanknotesPickerTest;
import org.assertj.core.api.AbstractIntegerAssert;
import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
    public void should_fail_should_return_empty_result_when_cannot_pick_banknotes_with_no_change_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_return_empty_result_when_cannot_pick_banknotes_with_no_change";
        var expectedTestedValue = "колличество рублей со сдачей";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_return_empty_result_when_cannot_pick_banknotes_with_no_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatThereIsChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_return_empty_result_when_cannot_pick_banknotes_with_no_change_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_return_empty_result_when_cannot_pick_banknotes_with_no_change";
        var expectedTestedValue = "колличество рублей со сдачей";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_return_empty_result_when_cannot_pick_banknotes_with_no_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatThereIsChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_fail_should_return_banknotes_when_can_pick_banknotes_with_no_change_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_return_banknotes_when_can_pick_banknotes_with_no_change";
        var expectedTestedValue = "колличество рублей без сдачи";
        assertThatTestFails(testedMethodName, banknotesPickerTest::should_return_banknotes_when_can_pick_banknotes_with_no_change);
        assertThatRequestedAmount(testedMethodName, expectedTestedValue)
            .isPositive()
            .satisfies(amount -> assertThatNoChange(amount, testedMethodName, expectedTestedValue));
    }

    @Test
    public void should_pass_should_return_banknotes_when_can_pick_banknotes_with_no_change_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_return_banknotes_when_can_pick_banknotes_with_no_change";
        var expectedTestedValue = "колличество рублей без сдачи";
        assertThatTestDoesNotFail(testedMethodName, banknotesPickerTest::should_return_banknotes_when_can_pick_banknotes_with_no_change);
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