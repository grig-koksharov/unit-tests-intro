package online_course_internals;

import banknotes.Banknote;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static online_course_internals.TestMode.isBuggyImplementation;
import static online_course_internals.TestedValues.recordTestedValue;

public class UnderTestLogic {

    public static Collection<Banknote> этотМетодНужноПротестировать(int amount) {
        recordTestedValue(amount);
        if (isBuggyImplementation()) {
            return pickBanknotes(amount).stream()
                .sorted(reverseOrder())
                .collect(toList());
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
