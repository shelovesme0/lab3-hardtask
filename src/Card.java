import java.time.LocalDate;

public abstract class Card {
    private final long id;
    private final CardType type;

    protected Card(long id, CardType type) {
        this.id = id;
        this.type = type;
    }

    public long getId() { return id; }

    public CardType getType() { return type; }

    public abstract boolean canPass(LocalDate today);

    public abstract void registerTrip();

    protected abstract String kind();

    public String category() { return type.getTitle() + ", " + kind(); }

    @Override
    public String toString() { return "Картка №" + id + " (" + category() + ")"; }
}
