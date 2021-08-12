package online_course_internals;

import card.Card;

import static online_course_internals.TestMode.isBuggyImplementation;
import static online_course_internals.TestedValues.recordTestedValue;

public class UnderTestLogic {

    public static boolean этотМетодНужноПротестировать(Card card) {
        recordTestedValue(card.balance);
        if (isBuggyImplementation()) {
            return card.balance <= 0;
        } else {
            return card.balance > 0;
        }
    }
}
