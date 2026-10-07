import java.time.LocalDate;

public enum TermType {
    MONTH("на місяць"), TEN_DAYS("на 10 днів");

    private final String title;

    TermType(String title) { this.title = title; }

    public String getTitle() { return title; }

    // Перший день, коли картка, видана from, вже не діє
    public LocalDate expiry(LocalDate from) {
        if (this == MONTH) {
            return from.plusMonths(1);
        }
        else {
            return from.plusDays(1);
        }
    }
}