public enum Difficulty {
    EASY("EASY"),
    MEDIUM("MEDIUM"),
    HARD("HARD");

    final private String value;
    Difficulty(String value) {
        this.value = value;
    }
    public String toString () {
        return this.value;
    }
}
