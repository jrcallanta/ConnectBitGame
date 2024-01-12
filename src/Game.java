import java.util.*;
import java.util.concurrent.TimeUnit;

public class Game {

    final private Difficulty difficulty;
    private Date startTime;
    private Date endTime;

    public enum InstructionDepth {
        FULL, SHORT, TURN
    }
    public Game (Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    public static void showInstructions () {
        showInstructions(InstructionDepth.FULL);
    }
    public static void showInstructions (InstructionDepth depth) {
        switch (depth) {
            case FULL -> {
                System.out.println();
                System.out.println("==============================================");
                System.out.println("[GOAL]: You are given a list of integers,");
                System.out.println("  all of which are representable by 4 bits.");
                System.out.println("  Arrange the integers such that any two");
                System.out.println("  consecutive integers only differ by 1 bit.");
                System.out.println("  The first and last integers will always be");
                System.out.println("  in their correct location.");
                System.out.println();
                System.out.println("[INSTRUCTIONS]: To submit an answer, simply");
                System.out.println("  type in a space-separated list of integers");
                System.out.println("  in any order you think satisfies");
                System.out.println("  the conditions.");
                System.out.println();
                System.out.println("  ex:   Given the following list: ");
                System.out.println("        [3  4  7  6  5]");
                System.out.println("        3  -  -  -  5");
                System.out.println();
                System.out.println("        The binary representations are");
                System.out.println("        3 -> 0011   4 -> 0100   5 -> 0101");
                System.out.println("        6 -> 0110   7 -> 0111");
                System.out.println();
                System.out.println("        3 is one bit away from 7, 0011 -> 0111");
                System.out.println("        7 is one bit away from 6, 0111 -> 0110");
                System.out.println("        6 is one bit away from 4, 0110 -> 0100");
                System.out.println("        4 is one bit away from 5, 0100 -> 0101");
                System.out.println();
                System.out.println("        Therefore, the solution is [3 7 6 4 5]");
                System.out.println();
                System.out.println("==============================================");
                System.out.println();
            }
            case SHORT -> {
                System.out.println("[GOAL]: Arrange the integers such that any two");
                System.out.println("  consecutive integers only differ by 1 bit.");
                System.out.println("  The first and last integers will always be");
                System.out.println("  in their correct location.");
                System.out.println("[INSTRUCTIONS]: Type in a space-separated");
                System.out.println("  list of integers in any order you think");
                System.out.println("  satisfies the conditions.");
                System.out.println();
            }
            default -> {}
        }
    }
    private void countdown() {
        try {
            for (int i = 0; i < 3; i++) {
                switch (i) {
                    case 0 -> System.out.print("READY... ");
                    case 1 -> System.out.print("SET... ");
                    case 2 -> System.out.print("GO!!!\n\n");
                    default -> {}
                }
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            // do nothing
        }
    }
    private Score getScore () {
        if (this.startTime == null || this.endTime == null) {
            return null;
        }

        int seconds = (int) TimeUnit.SECONDS.convert(this.endTime.getTime() - this.startTime.getTime(), TimeUnit.MILLISECONDS);
        return new Score(this.difficulty, seconds, 1);
    }
    private void startTimer () {
        this.startTime = new Date();
    }
    private void stopTimer () {
        this.endTime = new Date();
    }

    private void printWinnerResults (List<Integer> finalSolution, List<List<Integer>> allSolutions) {
        System.out.println("CORRECT!\n");
        String s = String.format("SOLUTION: %s", finalSolution);
        String line = String.format("%" + s.length() + "s", "").replace(" ", "-");
        System.out.println(line);
        System.out.printf("%s\n",s);
        System.out.print("BIT FLIPS:\n");
        Solution solution = new Solution(finalSolution);
        for (int i = 0; i < finalSolution.size(); i ++) {
            String v = solution.getElements().get(i) + " -> " + solution.getBinaries().get(i);
            System.out.printf("%" + s.length() + "s\n", v);
        }
        System.out.print("ALL SOLUTIONS:\n");
        allSolutions.forEach(sol ->
                System.out.printf("%" + s.length() + "s\n", sol)
        );
        System.out.println(line);
    }
    public Score play () {
        Scanner scanner = new Scanner(System.in);
        Generator generator = new Generator();
        switch (this.difficulty) {
            case EASY -> generator.generate(6);
            case MEDIUM -> generator.generate(8);
            case HARD -> generator.generate(10);
            default -> {}
        }

        generator.solve();
        countdown();
        startTimer();

        List<Integer> answer;
        boolean winner = false;
        while (true) {
            generator.printTargets();
            System.out.println();
            generator.printEnds();
            System.out.println();

            answer = new ArrayList<>();
            String answerLine = scanner.nextLine();
            if (answerLine.equalsIgnoreCase("q") || answerLine.toLowerCase().contains("quit")){
                System.out.println("\n[GAME OVER]");
                break;
            }

            Scanner lineScanner = new Scanner(answerLine);
            while (lineScanner.hasNextInt()) {
                answer.add(lineScanner.nextInt());
            }

            if (
                answer.size() != generator.getTargets().size() ||
                !generator.getSolutionsAsIntegerLists().contains(answer)
            ) {
                System.out.println("INCORRECT!\n");
            } else {
                stopTimer();
                winner = true;
                break;
            }
        }

        if (winner) {
            printWinnerResults(answer, generator.getSolutionsAsIntegerLists());
            return getScore();
        }


        return null;
    }

}
