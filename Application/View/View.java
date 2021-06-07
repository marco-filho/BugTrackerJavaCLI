package Application.View;

import java.util.InputMismatchException;
import java.util.Scanner;

import Auth.Session;

public class View {
    private static Scanner scanner = new Scanner(System.in);

    public void index() {
        int input = 0;
        System.out.println("1. Entrar");
        System.out.println("2. Registrar");

        try {
            input = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Entrada inválida, tente novamente.");
            index();
            return;
        }

        switch (input) {
            case 1:
                //signIn();
                break;
            case 2:
                //signUp();
                break;
            default:
                System.out.println("Entrada inválida, tente novamente.");
                index();
                return;
        }
    }
}
