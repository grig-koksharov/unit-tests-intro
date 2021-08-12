package card;

import static online_course_internals.UnderTestLogic.этотМетодНужноПротестировать;

public class Card {

    public final int balance;

    public Card(int balance) {
        this.balance = balance;
    }

    public boolean hasPositiveBalance() {
        return этотМетодНужноПротестировать(this);
    }
}