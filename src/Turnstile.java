import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public class Turnstile {
    private static final String UNREADABLE = "Картку не зчитано";

    private final CardRegistry registry;
    private final Map<String, int[]> stats = new TreeMap<>();
    private int allowed;
    private int denied;

    Turnstile(CardRegistry registry) { this.registry = registry; }

    public boolean pass(Card card, LocalDate today) {
        if (!registry.isIssued(card)) {
            return record(UNREADABLE, false);
        }
        if (!card.canPass(today)) {
            return record(card.category(), false);
        }

        card.registerTrip();
        return record(card.category(), true);
    }

    private boolean record(String category, boolean ok) {
        int[] counters = stats.computeIfAbsent(category, k -> new int[2]);

        if (ok) { counters[0]++; allowed++; } else { counters[1]++; denied++; }

        return ok;
    }

    public int getAllowed() { return allowed; }

    public int getDenied() { return denied; }

    public String totalReport() {
        return "Усього: дозволено " + allowed + ", відмовлено " + denied;
    }

    public String reportByCategory() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-32s %9s %10s%n", "Тип квитка", "Дозволено", "Відмовлено"));
        for (Map.Entry<String, int[]> e : stats.entrySet()) {
            sb.append(String.format("%-32s %9d %10d%n", e.getKey(), e.getValue()[0], e.getValue()[1]));
        }
        return sb.toString();
    }
}
