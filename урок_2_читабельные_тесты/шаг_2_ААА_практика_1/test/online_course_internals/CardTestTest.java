package online_course_internals;

import card.CardTest;
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

public class CardTestTest {

    private final CardTest cardTest = new CardTest();

    @Before
    public void before() {
        cleanup();
    }

    @After
    public void after() {
        givenCorrectImplementation();
    }

    @Test
    public void should_fail_should_have_positive_balance_when_balance_is_greater_than_0_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_have_positive_balance_when_balance_is_greater_than_0";
        var unexpectedTestedValue = "карту с непозитивным балансом";
        var expectedTestedValue = "карту с позитивным балансом";
        assertThatTestFails(testedMethodName, cardTest::should_have_positive_balance_when_balance_is_greater_than_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isPositive();
    }

    @Test
    public void should_pass_should_have_positive_balance_when_balance_is_greater_than_0_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_have_positive_balance_when_balance_is_greater_than_0";
        var unexpectedTestedValue = "карту с непозитивным балансом";
        var expectedTestedValue = "карту с позитивным балансом";
        assertThatTestDoesNotFail(testedMethodName, cardTest::should_have_positive_balance_when_balance_is_greater_than_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isPositive();
    }

    @Test
    public void should_fail_should_not_have_positive_balance_when_balance_is_less_than_0_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_not_have_positive_balance_when_balance_is_less_than_0";
        var unexpectedTestedValue = "карту с позитивным балансом";
        var expectedTestedValue = "карту с негативным балансом";
        assertThatTestFails(testedMethodName, cardTest::should_not_have_positive_balance_when_balance_is_less_than_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isNegative();
    }

    @Test
    public void should_pass_should_not_have_positive_balance_when_balance_is_less_than_0_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_not_have_positive_balance_when_balance_is_less_than_0";
        var unexpectedTestedValue = "карту с позитивным балансом";
        var expectedTestedValue = "карту с негативным балансом";
        assertThatTestDoesNotFail(testedMethodName, cardTest::should_not_have_positive_balance_when_balance_is_less_than_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isNegative();
    }

    @Test
    public void should_fail_should_not_have_positive_balance_when_balance_is_equal_to_0_when_buggy_implementation() {
        // Arrange
        givenBuggyImplementation();

        // Act / Assert
        var testedMethodName = "should_not_have_positive_balance_when_balance_is_equal_to_0";
        var unexpectedTestedValue = "карту с балансом, неравным нулю";
        var expectedTestedValue = "карту с балансом, равным нулю";
        assertThatTestFails(testedMethodName, cardTest::should_not_have_positive_balance_when_balance_is_equal_to_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isZero();
    }

    @Test
    public void should_pass_should_not_have_positive_balance_when_balance_is_equal_to_0_when_correct_implementation() {
        // Arrange
        givenCorrectImplementation();

        // Act / Assert
        var testedMethodName = "should_not_have_positive_balance_when_balance_is_equal_to_0";
        var unexpectedTestedValue = "карту с балансом, неравным нулю";
        var expectedTestedValue = "карту с балансом, равным нулю";
        assertThatTestDoesNotFail(testedMethodName, cardTest::should_not_have_positive_balance_when_balance_is_equal_to_0);
        assertThatCardBalance(testedMethodName, unexpectedTestedValue, expectedTestedValue)
            .isZero();
    }

    private AbstractIntegerAssert<?> assertThatCardBalance(String testedMethodName, String unexpectedTestedValue, String expectedTestedValue) {
        assertThat(testedValues())
            .overridingErrorMessage(notSingleTestedValue(testedMethodName))
            .hasSize(1);
        return assertThat(testedValues()).first().asInstanceOf(INTEGER)
            .overridingErrorMessage(unexpectedTestedValue(testedMethodName, unexpectedTestedValue, expectedTestedValue));
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
}