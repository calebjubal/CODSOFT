import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        System.out.println("!--------- NUMERO CINCO ---------!");
        System.out.println("Welcome User to Caleb's Brand new guessing game, \nwhere you get the opportunity to guess a number\nThere is a total of 5 rounds where total score will be 500 points \nbut you will get only 10 tries each round \nalso after each attempt you will lose 10 points \nSo let's see whether you sync with the computer or NOT");
        System.out.println("NOTE: Points are on the basis of no. of attempts made");

        Scanner myObj = new Scanner(System.in);
        String name = "";
        try {
            boolean validName = false;
            while (!validName) {
                System.out.print("USERNAME: ");
                name = myObj.nextLine();
                if (name.matches("^[a-zA-Z]+$")) {
                    validName = true;
                } else {
                    System.out.println("ERROR: Username should not contain numbers or special characters. Please enter a valid username.");
                }
            }

            /* Create a new random number generator */
            Random random = new Random();

            /* Comments after each wrong attempt */
            ArrayList<String> comments = new ArrayList<String>();
            comments.add("You got it right in your first try ");
            comments.add("Ah!, you were pretty close \nTry Again");
            comments.add("Good guess but not the right one \nTry Again");
            comments.add("Wrong Guess, Last chance \nTry for the last time");

            int totalScore = 0;

            // 5 rounds
            for (int round = 1; round <= 5; round++) {
                System.out.println("!--------- ROUND " + round + " ---------!");
                int randomNumber = random.nextInt(100) + 1;
                int roundScore = 100;

                for (int attempt = 1; attempt <= 10; attempt++) {
                    System.out.print("Enter guess (1-100): ");
                    int guess = 0;
                    boolean validInput = false;

                    while (!validInput) {
                        try {
                            guess = myObj.nextInt();
                            if (guess >= 1 && guess <= 100) {
                                validInput = true;
                            } else {
                                System.out.println("ERROR: Please enter a number between 1 and 100.");
                                System.out.print("Enter guess (1-100): ");
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("ERROR: Please enter a valid integer.");
                            myObj.next(); // Clear the invalid input
                            System.out.print("Enter guess (1-100): ");
                        }
                    }

                    if (guess == randomNumber) {
                        System.out.println("Congratulations!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                        if (attempt == 1) {
                            System.out.println(comments.get(0) + name);
                        } else {
                            System.out.println("That is the right guess, " + name);
                        }
                        roundScore = 100 - (10 * (attempt - 1));
                        System.out.println("Score: " + roundScore);
                        totalScore += roundScore;
                        break;
                    } else {
                        roundScore -= 10;
                        if (attempt < 10) {
                            if (Math.abs(guess - randomNumber) <= 8) {
                                System.out.println(comments.get(1));
                            } else {
                                System.out.println(comments.get(2));
                            }
                        } else {
                            System.out.println(comments.get(3));
                            System.out.println("The correct number was: " + randomNumber);
                        }
                    }
                }

                if (roundScore == 0) {
                    System.out.println("Score: 0");
                }
            }

            System.out.println("!--------- GAME OVER ---------!");
            System.out.println(name + ", your total score is: " + totalScore);
        } finally {
            myObj.close();
        }
    }
}
