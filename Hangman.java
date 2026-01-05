import java.util.Scanner;
import java.util.Random;

public class Hangman {

    static String[] words = {"java", "computer", "programming", "keyboard", "monitor", "mouse", "software"};
    static int maxErrors = 6;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        String word = words[random.nextInt(words.length)];
        char[] guessedWord = new char[word.length()];
        boolean[] guessedLetters = new boolean[26];
        int errors = 0;

        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        while (errors < maxErrors) {
            printHangman(errors);
            printWord(guessedWord);
            printGuessedLetters(guessedLetters);

            System.out.print("\nGuess a letter: ");
            String input = scanner.nextLine().toLowerCase();

            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Please enter a single letter.");
                continue;
            }

            char letter = input.charAt(0);
            int letterIndex = letter - 'a';

            if (guessedLetters[letterIndex]) {
                System.out.println("You already guessed that letter.");
                continue;
            }

            guessedLetters[letterIndex] = true;

            boolean found = false;
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == letter) {
                    guessedWord[i] = letter;
                    found = true;
                }
            }

            if (!found) {
                errors++;
                System.out.println("Wrong! Attempts left: " + (maxErrors - errors));
            }

            if (String.valueOf(guessedWord).equals(word)) {
                System.out.println("\nCongratulations! You won!");
                System.out.println("The word was: " + word);
                scanner.close();
                return;
            }
        }

        printHangman(errors);
        System.out.println("\nGame Over! You lost.");
        System.out.println("The word was: " + word);
        scanner.close();
    }

    static void printHangman(int errors) {
        System.out.println("\n  +---+");
        System.out.println("  |   |");
        System.out.println("  |   " + (errors >= 1 ? "O" : " "));
        System.out.println("  |  " + (errors >= 3 ? "/" : " ") + (errors >= 2 ? "|" : " ") + (errors >= 4 ? "\\" : " "));
        System.out.println("  |  " + (errors >= 5 ? "/" : " ") + " " + (errors >= 6 ? "\\" : " "));
        System.out.println("  |");
        System.out.println("=========");
    }

    static void printWord(char[] guessedWord) {
        System.out.print("\nWord: ");
        for (char c : guessedWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    static void printGuessedLetters(boolean[] guessedLetters) {
        System.out.print("Used letters: ");
        for (int i = 0; i < 26; i++) {
            if (guessedLetters[i]) {
                System.out.print((char)('a' + i) + " ");
            }
        }
    }
}
