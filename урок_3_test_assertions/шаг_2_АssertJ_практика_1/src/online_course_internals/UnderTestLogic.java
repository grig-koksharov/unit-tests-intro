package online_course_internals;

import banknotes.Banknote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static online_course_internals.TestMode.isBuggyImplementation;
import static online_course_internals.TestedValues.recordTestedValue;

public class UnderTestLogic {

    public static Collection<Banknote> этотМетодНужноПротестировать(int amount) {
        recordTestedValue(amount);
        if (isBuggyImplementation()) {
            return pickBanknotes(amount + 50);
        } else {
            return pickBanknotes(amount);
        }
    }

    private static ArrayList<Banknote> pickBanknotes(int amount) {
        final var banknotes = new ArrayList<Banknote>();
        var restAmount = amount;

        for (Banknote banknote: Banknote.values()) {
            final var banknotesNumber = restAmount / banknote.value;

            banknotes.addAll(Stream.generate(() -> banknote)
                .limit(banknotesNumber)
                .collect(toList()));

            restAmount -= banknotesNumber * banknote.value;
        }
        return banknotes;
    }
}
