import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    public static final int FARE = 8; // вартість поїздки

    private final Map<Long, Card> cards = new HashMap<>();
    private long nextId = 1;

    public TermCard issueTermCard(CardType type, TermType term, LocalDate issued) {
        return register(new TermCard(nextId++, type, term, issued));
    }

    public TripCard issueTripCard(CardType type, int trips) {
        return register(new TripCard(nextId++, type, trips));
    }

    public AccumulativeCard issueAccumulativeCard(int initialAmount) {
        AccumulativeCard card = new AccumulativeCard(nextId++, FARE);
        if (initialAmount > 0) card.topUp(initialAmount);
        return register(card);
    }

    public boolean isIssued(Card card) {
        return card != null && cards.get(card.getId()) == card;
    }

    private <T extends Card> T register(T card) {
        cards.put(card.getId(), card);
        return card;
    }
}
