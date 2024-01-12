import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static Difficulty askDifficulty(Scanner scanner) {

        while (true) {
            System.out.println();
            System.out.println("CHOOSE DIFFICULTY:");
            System.out.println("E) EASY   (6 integers)");
            System.out.println("M) MEDIUM (8 integers)");
            System.out.println("H) HARD   (10 integers)");
            System.out.println();
            System.out.print("> ");

            switch (scanner.nextLine().toLowerCase().trim()) {
                case "e" -> {
                    return Difficulty.EASY;
                }
                case "m" -> {
                    return Difficulty.MEDIUM;
                }
                case "h" -> {
                    return Difficulty.HARD;
                }
                case "quit" -> {
                    return null;
                }
                default -> {}
            }
        }
    }
    public static String askPlayAgain(Scanner scanner) {
        while (true) {
            System.out.println("PLAY AGAIN?");
            System.out.println("Y) YES");
            System.out.println("N) NO");
            System.out.println("D) CHANGE DIFFICULTY");
            System.out.println();
            System.out.print("> ");

            switch (scanner.nextLine().toLowerCase().trim()) {
                case "y", "yes" -> {
                    return "YES";
                }
                case "n", "no", "quit" -> {
                    return "NO";
                }
                case "d" -> {
                    return "CHANGE_DIFFICULTY";
                }
                default -> {}
            }
        }
    }
    public static void printScores(ArrayList<Score> scores, Score last) {
        scores.sort(Score::byScore);
        System.out.print(String.format("%36s\n", "").replace(" ", "-"));
        System.out.printf("%18s%-18s\n","SCORE","BOARD");
        System.out.printf("%-36s\n","");
        for(int i = 0; i < scores.size(); i++) {
            String s = String.format("%2d.%s", i+1, scores.get(i).formatScore("td"));
            System.out.printf("%-36s", s);
            System.out.println(scores.get(i) == last ? "  <--" : "" );
        }
        System.out.print(String.format("%36s\n", "").replace(" ", "-"));
        System.out.println();
    }

    public static void main(String[] args) {
        Game.showInstructions();
        ArrayList<Score> scores = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Difficulty difficulty = askDifficulty(scanner);

        while (difficulty != null) {
            Game game = new Game(difficulty);

            System.out.println();
            Score score = game.play();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                // do nothing
            }

            if (score != null) scores.add(score);
            System.out.println();
            printScores(scores, score);

            switch (askPlayAgain(scanner)) {
                case "NO" -> difficulty = null;
                case "CHANGE_DIFFICULTY" -> difficulty = askDifficulty(scanner);
                default -> {}
            }
        }

    }
}