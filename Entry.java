import Application.Controller.TicketsController;

import Auth.Session;

import tests.*;

public class Entry {
    public static void main(String[] args) {
        Session session = new Session();

        do {
            if (!session.isUserAuthenticated())
                session.signIn();
            else
                TicketsController.index();
        } while(true);
    }

    static void tests() {
        System.out.println("=== tests begin ===");

        //Tests.testGSONstuff();
        //Tests.testSaveChangesAndReadJsonFile();

        System.out.println("=== end ===");
    }
}