package GameImplementation;

import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Difficulty: ");
        int depth = scanner.nextInt();

        Controller game = new Controller();
        game.start('X',depth);
    }
}
