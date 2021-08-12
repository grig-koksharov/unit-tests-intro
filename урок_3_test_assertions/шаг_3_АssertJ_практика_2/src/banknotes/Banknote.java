package banknotes;

public enum Banknote {
    FIVE_THOUSAND(5000),
    TWO_THOUSAND(2000),
    THOUSAND(1000),
    FIVE_HUNDRED(500),
    TWO_HUNDRED(200),
    HUNDRED(100),
    FIFTY(50);

    public final int value;

    Banknote(int value) {
        this.value = value;
    }
}
