import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz_Application {

    public static void main(String[] args) {
        List<Question> questions = new ArrayList<>();


        questions.add(new Question("Which of the three banks will be merged with the other two to create India’s third-largest bank?",
                new String[]{"Punjab national Bank", "State Bank Of India ", "Indian Bank"}, 1, 10));
        questions.add(new Question("What is the name of the weak zone of the earth’s crust?",
                new String[]{"Sesmic", "Formic", "Cosmic"}, 0, 10));
        questions.add(new Question("Where was India’s first national Museum opened?",
                new String[]{"Hyderabad", "Delhi", "Mumbai"}, 2, 10));
        questions.add(new Question("In 2019, Which popular singer was awarded the Bharat Ratna award?",
                new String[]{"Lata mangeshkar", "Asha bhonsle", "Bhupen hazarika"}, 2, 10));
        questions.add(new Question("How many continents are there in the world?",
                new String[]{"7", "17", "5"}, 0, 10));
        questions.add(new Question("The world’s nation 5G mobile network was launched by which country?",
                new String[]{"Japan", "Asia", "South Korea"}, 2, 10));
        questions.add(new Question("When was Pravasi Bhartiya Divas held in Varanasi?",
                new String[]{"2017", "2019", "2020"}, 1, 10));
        questions.add(new Question("Name the largest mammal?",
                new String[]{"Blue Whale", "Giraffe", "Elephant"}, 0, 10));
        questions.add(new Question("Vijay Singh (golf player) is from which country?",
                new String[]{"UK", "fiji", "India"}, 1, 10));
        questions.add(new Question("What country are the Pyramids in?",
                new String[]{"china", "South America5", "Egypt "}, 2, 10));
        questions.add(new Question("What is the full form of DRDL?",
                new String[]{"Differential Research and Documentation Laboratory", "Department of Research and Development Laboratory", "None of the above"}, 1, 10));
        questions.add(new Question("The green planet in the solar system is?",
                new String[]{"uranus", "Earth", "Mars"}, 0, 10));

        Quizgame quiz = new Quizgame(questions);
        quiz.startQuiz();
    }
}

class Question {

    private String question;
    private String[] options;
    private int correctAnswerIndex;
    private int timeLimit;

    public Question(String question, String[] options, int correctAnswerIndex, int timeLimit) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.timeLimit = timeLimit;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public int getTimeLimit() {
        return timeLimit;
    }
}

class Quizgame {

    private List<Question> questions;
    private int score;

    public Quizgame(List<Question> questions) {
        this.questions = questions;
        this.score = 0;
    }



    public void startQuiz() {
        Scanner scanner = new Scanner(System.in);
        for (Question question : questions) {
            displayQuestion(question);
            int userAnswerIndex = getUserAnswer(question, scanner);
            evaluateAnswer(question, userAnswerIndex);
        }
        showResult();
    }

    private void displayQuestion(Question question) {
        System.out.println(question.getQuestion());
        for (int i = 0; i < question.getOptions().length; i++) {
            System.out.println((i + 1) + ". " + question.getOptions()[i]);
        }
    }

    private int getUserAnswer(Question question, Scanner scanner) {
        long startTime = System.currentTimeMillis();
        int answerIndex;
        do {
            System.out.print("Enter your answer (1-" + question.getOptions().length + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number between 1 and " + question.getOptions().length + ".");
                scanner.next();
            }
            answerIndex = scanner.nextInt() - 1;

            long currentTime = System.currentTimeMillis();
            long remainingTime = question.getTimeLimit() * 1000 - (currentTime - startTime);
            if (remainingTime <= 0) {
                System.out.println("Time's up! No answer submitted.");
                break;
            }
        } while (answerIndex < 0 || answerIndex >= question.getOptions().length);
        return answerIndex;
    }

    private void evaluateAnswer(Question question, int userAnswerIndex) {
        if (userAnswerIndex == question.getCorrectAnswerIndex()) {
            System.out.println("Correct!");
            score++;
        } else {
            System.out.println("Incorrect. The correct answer is " + question.getOptions()[question.getCorrectAnswerIndex()]);
        }}


    private void showResult() {
        System.out.println("--------------------");
        System.out.println("Final Score: " + score + " out of " + questions.size());
        System.out.println("--------------------");
    }
}

