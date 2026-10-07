import java.time.LocalDate;

public class AccumulativeCard extends Card {
    private final int fare; // вартість поїздки
    private int balance;

    AccumulativeCard(long id, int fare) {
        super(id, CardType.REGULAR);
        this.fare = fare;
    }

    public void topUp(int amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сума поповнення має бути додатною");
        balance += amount;
    }

    public int getBalance() { return balance; }

    @Override
    public boolean canPass(LocalDate today) { return balance >= fare; }

    @Override
    public void registerTrip() { balance -= fare; }

    @Override
    protected String kind() { return "накопичувальна"; }
}
