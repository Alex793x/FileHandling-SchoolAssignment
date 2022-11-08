package myfriends;

import java.util.Scanner;

public class UI {

    // Attributes ------------------------------------
    Scanner in = new Scanner(System.in);


    // Behaviors (Methods) --------------------------------------
    public String readLine() {

        // If input isn't a String a recursive instance of the method will happen, ensuring input always is a String
        if (in.hasNextLine()) {
            return in.nextLine();
        } else {
            System.out.println("You entered something invalid, try again!");
            return readLine();
        }
    }

    public int readInt() {
        if (in.hasNextInt()) {
            int readInt = in.nextInt();
            in.nextLine();
            return readInt;
        } else {
            System.out.println("Not valid input, please enter a valid ID number");
            in.nextLine();
            return readInt();
        }
    }
}
