package myfriends;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import Menu.Menu;

public class Main {

    // Utilities ----------------------------------------------------
    UI ui = new UI();


    // Attributes ---------------------------------------------------
    ArrayList<Friend> friends;

    Menu myMenu = new Menu("MENU", "Chosen Option: ", new String[] {
            "1: Show list of friends",
            "2: Enter new friend",
            "3: Delete friend",
            "4: Load list",
            "9: Quit"
    });

    // FILE HANDLER ------------------------------------------------
    FileOutputStream FRIENDLIST;

    {
        try {

            FRIENDLIST = new FileOutputStream("friendList.txt", true);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);

        }
    }


    // Constructor -------------------------------------------------
    Main() {
        friends = new ArrayList<>();
        mainMenu();
    }

    // Behaviors (Methods) ------------------------------------------
    private void mainMenu() {
        boolean keepRunning = false;

        while(!keepRunning) {
            myMenu.printMenu();

            switch (myMenu.readChoice()) {
                case 1:
                    System.out.println("\nSHOW FRIEND LIST");
                    showFriendList();
                    break;

                case 2:
                    System.out.println("\nADD FRIEND");
                    addFriend();
                    break;

                case 3:
                    System.out.println("\nREMOVE FRIEND");
                    deleteFriend();
                    break;

                case 4:
                    System.out.println("\nLOAD LIST");
                    loadList();
                    break;

                case 9:
                    System.out.println("\nQUIT");
                    quit();
                    keepRunning = true;
                    System.out.println("Quitting program");
                    break;
            }
        }
    }

    // Menu option 1 ----------------------------------------
    private void showFriendList() {

        for (Friend friend : friends) {
            System.out.println(friend);
        }
    }

    // Menu option 2 -----------------------------------------
    private void addFriend() {
        System.out.println("Friend name: ");
        String name = ui.readLine();        // Scan name Input
        System.out.println("Friend phone number: ");
        String phoneNum = ui.readLine();    // Scan phoneNumber Input
        System.out.println("Friend email: ");
        String email = ui.readLine();       // Scan email Input

        Friend newFriend = new Friend(name, phoneNum, email);   // Save Inputs in variable
        friends.add(newFriend);                                 // Add variable to ArrayList<Friend> friends

        saveList(email);
    }

    // Menu option 3 -----------------------------------------
    private void deleteFriend() {
        System.out.print("Who do you want to remove from friend list? - Please enter email: ");
        String friendEmail = ui.readLine();
        int emailCount = 1;

        for (Friend friend : friends) {

            if (friend.getEmail().equalsIgnoreCase(friendEmail)) {
                System.out.println("Friend: " + friend.getName() + " removed from Friend List");
                friends.remove(friend);
                break;

            } else {

                emailCount++;       // Count everytime email doesn't match a friends email
            }

            // If no email match is found in the list, print mismatch message
            if (emailCount == friends.size()) {
                System.out.println("No email match found");
            }
        }

    }

    // Menu option 3 Save File part -----------------------------------------
    private void saveList(String email) {


        for (Friend friend : friends) {

            if (friend.getEmail().equalsIgnoreCase(email)) {

                try {
                    StringBuilder sb = new StringBuilder();

                    // Write user information in FILE - USERFILELIST
                    sb.append("\nName: ").append(friend.getName());             // String Build ID
                    sb.append("\nPhone Number: ").append(friend.getPhone());    // Append next part (name) - to String Builder
                    sb.append("\nEmail: ").append(friend.getEmail());           // Append next part (Password) - to String Builder
                    sb.append("\n------------------------------------------");  // Append dashed lines to String Builder
                    sb.append("\n");                                            // Append newline
                    FRIENDLIST.write(sb.toString().getBytes());              // Write the whole String build in file

                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }
    }

    // Menu option 4 -----------------------------------------
    private void loadList() {

        try {

            Scanner scan = new Scanner(new File("friendList.txt"));         // The file to scan

            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());        // Printing each line from file
            }

            scan.close();                                   // Closing Scanner

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Menu option 9 --------------------------------------------
    private void quit() {
        System.out.println("Thanks for using MY-FRIENDLIST-APP");
    }


}
