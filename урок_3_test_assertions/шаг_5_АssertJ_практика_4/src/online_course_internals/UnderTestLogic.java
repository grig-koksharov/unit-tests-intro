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

    public static Collection<Banknote> этотМетодНужноПротестировать(int amount) {
        recordTestedValue(amount);
        if (isBuggyImplementation()) {
            throw new IllegalArgumentException("Incorrect implementation always throwing errors");
        } else {
            if (amount < 0) {
                throw new IllegalArgumentException(String.format("Requested amount cannot be negative while it is %s", amount));
            }
            if (amount == 0) {
                throw new IllegalArgumentException(String.format("Requested amount cannot be zero while it is %s", amount));
            }
            if (amount % 50 != 0) {
                throw new IllegalArgumentException(String.format("There is change when picking banknotes for %s", amount));
            }
        }
        return pickBanknotes(amount);
    }

    private static Collection<Banknote> pickBanknotes(int amount) {
        final var banknotes = new ArrayList<Banknote>();
        var restAmount = amount;

        for (Banknote banknote : Banknote.values()) {
            final var banknotesNumber = restAmount / banknote.value;

            banknotes.addAll(Stream.generate(() -> banknote)
                .limit(banknotesNumber)
                .collect(toList()));

            restAmount -= banknotesNumber * banknote.value;
        }

        return banknotes;
    }
}
