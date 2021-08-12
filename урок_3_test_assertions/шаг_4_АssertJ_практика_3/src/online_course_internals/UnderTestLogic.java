package online_course_internals;

import banknotes.Banknote;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static banknotes.Banknote.FIFTY;
import static banknotes.Banknote.THOUSAND;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;
import static online_course_internals.TestMode.isBuggyImplementation;
import static online_course_internals.TestedValues.recordTestedValue;

public class UnderTestLogic {

    public static Optional<Collection<Banknote>> этотМетодНужноПротестировать(int amount) {
        recordTestedValue(amount);
        final var expectedResult = pickBanknotes(amount);
        if (isBuggyImplementation()) {
            if (expectedResult.isPresent()) {
                return Optional.of(List.of(THOUSAND));
            } else {
                return Optional.of(List.of(FIFTY));
            }
        } else {
            return expectedResult;
        }
    }

    private static Optional<Collection<Banknote>> pickBanknotes(int amount) {
        final var banknotes = new ArrayList<Banknote>();
        var restAmount = amount;

        for (Banknote banknote: Banknote.values()) {
            final var banknotesNumber = restAmount / banknote.value;

            banknotes.addAll(Stream.generate(() -> banknote)
                .limit(banknotesNumber)
                .collect(toList()));

            restAmount -= banknotesNumber * banknote.value;
        }

        if (restAmount == 0) {
            return Optional.of(banknotes);
        } else {
            return Optional.empty();
        }
    }
}
