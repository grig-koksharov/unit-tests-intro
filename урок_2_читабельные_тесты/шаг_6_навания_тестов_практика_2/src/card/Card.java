package card;

public class Card {

    public final int balance;

    public Card(int balance) {
        this.balance = balance;
    }

    public boolean isBeautiful() {
        final var eachDigitIsSeven = Integer.toString(balance).chars()
            .allMatch(balanceChar -> balanceChar == '7');
        return eachDigitIsSeven;
    }
}