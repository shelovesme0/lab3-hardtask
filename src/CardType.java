public enum CardType {
    PUPIL("Учнівська"), STUDENT("Студентська"), REGULAR("Звичайна");

    private final String title;

    CardType(String title) { this.title = title; }

    public String getTitle() { return title; }
}
