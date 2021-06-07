import Application.Controller.TicketsController;
import Application.View.View;
import Auth.Session;

import tests.*;

public class Entry {
    public static void main(String[] args) {
        do {
            if (!session.isUserAuthenticated())
                View.index();
            else
                //TicketsController.index();
        } while(true);
    }

    static void tests() {
        System.out.println("=== tests begin ===");

        //Tests.testGSONstuff();
        //Tests.testSaveChangesAndReadJsonFile();

        System.out.println("=== end ===");
    }
}