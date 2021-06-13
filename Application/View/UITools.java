package Application.View;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UITools {
    private static Scanner scanner = new Scanner(System.in);

    public static int inputValidation(int higherOption) {
        int input = 0;
        boolean valid = false;

        do {
            try {
                input = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, tente novamente.");
                scanner.next();
                continue;
            }

            if (input > higherOption || input < 0) {
                System.out.println("Entrada inválida, tente novamente.");
                continue;
            }

            valid = true;
            UITools.clearConsole();
        } while (!valid);
        
        return input;
    }
    
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException | IOException e) {
        }
    }
}
