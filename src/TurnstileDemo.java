import java.time.LocalDate;

public class TurnstileDemo {
    private static Turnstile turnstile;

    private static void attempt(Card card, LocalDate day) {
        boolean ok = turnstile.pass(card, day);
        System.out.printf("%s  %-45s -> %s%n", day, card == null ? "нечитабельна картка" : card,
                ok ? "ПРОХІД ДОЗВОЛЕНО" : "ВІДМОВА");
    }

    public static void main(String[] args) {
        CardRegistry registry = new CardRegistry();
        turnstile = new Turnstile(registry);
        LocalDate today = LocalDate.of(2026, 10, 1);

        TermCard studentMonth = registry.issueTermCard(CardType.STUDENT, TermType.MONTH, today);
        TermCard pupilTenDays = registry.issueTermCard(CardType.PUPIL, TermType.TEN_DAYS, today);
        TripCard regularFive = registry.issueTripCard(CardType.REGULAR, 5);
        TripCard studentTen = registry.issueTripCard(CardType.STUDENT, 10);
        AccumulativeCard wallet = registry.issueAccumulativeCard(20); // 20 грн = 2 поїздки

        // Звичайні проходи
        attempt(studentMonth, today);
        attempt(pupilTenDays, today);
        attempt(studentTen, today);

        // Картка на 5 поїздок: шоста спроба — відмова
        for (int i = 0; i < 6; i++) {
            attempt(regularFive, today);
        }

        // Накопичувальна: грошей на 2 поїздки, далі відмова, після поповнення знову працює
        for (int i = 0; i < 3; i++) {
            attempt(wallet, today);
        }
        wallet.topUp(10);
        attempt(wallet, today);

        // Прострочені картки
        attempt(pupilTenDays, today.plusDays(9));   // останній день дії
        attempt(pupilTenDays, today.plusDays(10));  // прострочена
        attempt(studentMonth, today.plusMonths(1)); // прострочена

        // Дані не зчитано / картка не з реєстру
        attempt(null, today);
        attempt(new TripCard(999, CardType.REGULAR, 5), today);

        System.out.println();
        System.out.println(turnstile.totalReport());
        System.out.println();
        System.out.print(turnstile.reportByCategory());
    }
}
