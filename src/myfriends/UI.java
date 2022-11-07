package myfriends;

import java.io.File;
import java.util.Scanner;

public class UI {

    // Attributes ------------------------------------
    Scanner in = new Scanner(System.in);



    // Behaviors --------------------------------------
    public String readLine() {
        if (in.hasNextLine()) {
            return in.nextLine();
        } else {
            System.out.println("You entered something invalid, try again!");
            return readLine();
        }
    }
}
