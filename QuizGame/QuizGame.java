import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizGame {
    private static Scanner scanner = new Scanner(System.in);
    private static Timer timer = new Timer();
    private static int score = 0;
    private static int currentQuestion = 0;
    private static boolean timeUp = false;

    private static String[] questions = {
            "What is the capital of France?",
            "Which planet is known as the Red Planet?",
            "What is the largest mammal on Earth?"
    };

    private static String[][] options = {
            {"A) Berlin", "B) Madrid", "C) Paris", "D) Rome"},
            {"A) Venus", "B) Mars", "C) Jupiter", "D) Saturn"},
            {"A) Elephant", "B) Blue Whale", "C) Giraffe", "D) Hippopotamus"}
    };

    private static char[] correctAnswers = {'C', 'B', 'B'};

    public static void main(String[] args) {
        displayNextQuestion();
    }

    private static void displayNextQuestion() {
        if (currentQuestion < questions.length && !timeUp) {
            System.out.println(questions[currentQuestion]);
            for (String option : options[currentQuestion]) {
                System.out.println(option);
            }

            startTimer();

            char userAnswer = getUserAnswer();
            checkAnswer(userAnswer);

            currentQuestion++;
            displayNextQuestion();
        } else {
            displayResult();
        }
    }

    private static void startTimer() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                timeUp = true;
                timer.cancel();
                displayNextQuestion();
            }
        }, 15000); // 15 seconds timer
    }

    private static char getUserAnswer() {
        System.out.print("Your answer: ");
        return Character.toUpperCase(scanner.next().charAt(0));
    }

    private static void checkAnswer(char userAnswer) {
        if (!timeUp) {
            if (userAnswer == correctAnswers[currentQuestion]) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Incorrect. The correct answer is " + correctAnswers[currentQuestion] + "\n");
            }
            timer.cancel();
        }
    }

    private static void displayResult() {
        System.out.println("Quiz completed!");
        System.out.println("Your final score: " + score + "/" + questions.length);

        System.exit(0); // Terminate the program
    }
}
