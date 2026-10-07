import java.time.LocalDate;

public class TermCard extends Card {
    private final TermType term;
    private final LocalDate validUntil; // останній день дії (включно)

    TermCard(long id, CardType type, TermType term, LocalDate issued) {
        super(id, type);
        this.term = term;
        this.validUntil = term.expiry(issued).minusDays(1);
    }

    public TermType getTerm() { return term; }

    public LocalDate getValidUntil() { return validUntil; }

    @Override
    public boolean canPass(LocalDate today) { return !today.isAfter(validUntil); }

    @Override
    public void registerTrip() { }

    @Override
    protected String kind() { return term.getTitle(); }
}