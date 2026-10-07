import java.time.LocalDate;

public class TripCard extends Card {
    private final int initialTrips;
    private int tripsLeft;

    TripCard(long id, CardType type, int trips) {
        super(id, type);
        if (trips != 5 && trips != 10) {
            throw new IllegalArgumentException("Картка може бути лише на 5 або 10 поїздок");
        }
        this.initialTrips = trips;
        this.tripsLeft = trips;
    }

    public int getTripsLeft() { return tripsLeft; }

    @Override
    public boolean canPass(LocalDate today) { return tripsLeft > 0; }

    @Override
    public void registerTrip() { tripsLeft--; }

    @Override
    protected String kind() { return "на " + initialTrips + " поїздок"; }
}
