public class Score {
    final private Difficulty difficulty;
    final private int timeInSeconds;
    final private int scoreValue;

    public Score(Difficulty difficulty, int timeInSeconds, int multiplier) {
        this.difficulty = difficulty;
        this.timeInSeconds = timeInSeconds;
        this.scoreValue = timeInSeconds * multiplier;
    }
    public static int byScore (Score a, Score b) {
        return Integer.compare(a.getScoreValue(), b.getScoreValue());
    }
    public String getTimeAsString () {
        String min = String.format("%2d", timeInSeconds/60);
        String sec = String.format("%2d", timeInSeconds%60);
        return min + "m" + sec + "s";
    }
    public int getTimeInSeconds () {
        return timeInSeconds;
    }
    public int getScoreValue() {
        return scoreValue;
    }
    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String formatScore(String flags) {
        flags = flags.toLowerCase();
        StringBuilder sb = new StringBuilder();

        if (flags.contains("v"))
            sb.append(String.format("%12s", this.getScoreValue()));
        if (flags.contains("t"))
            sb.append(String.format("%12s", this.getTimeAsString()));
        if (flags.contains("d"))
            sb.append(String.format("%12s", this.getDifficulty()));

        return sb.toString();
    }

    public String toString() {
        return String.format("%12s%12s%12s",
                getScoreValue(),
                getTimeAsString(),
                getDifficulty()
        );
        //return getScoreValue() + "\t\t\t" + getDifficulty().toString().toUpperCase() + "\t\t\t" + getNumOfFlips() + "\t\t\t" + getTimeAsString();
    }
}
