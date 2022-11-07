package useradmin;

import Menu.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // Utilities ------------------------------------------
    Scanner in = new Scanner(System.in);

    // Attributes --------------------------------------------
    ArrayList<User> users;

    Menu myMenu = new Menu("MENU", "Please choose option: ", new String[] {
            "1: View user list",
            "2: Create new user",
            "3: Delete user",
            "4: Read file",
            "9: Quit"
    });


    // File HANDLER -----------------------------------------
    FileOutputStream USERLISTFILE;
    {
        try {
            USERLISTFILE = new FileOutputStream("userlist.txt", true);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Constructor -------------------------------------------
    Main() {
        users = new ArrayList<>();
        mainMenu();
    }


    // Behaviors (Methods) ------------------------------------
    public void mainMenu() {
        boolean keepRunning = false;

        while (!keepRunning) {
            myMenu.printMenu();

            switch (myMenu.readChoice()) {
                case 1:
                    System.out.println("VIEW USER LIST\n");
                    viewUserList();
                    break;

                case 2:
                    System.out.println("CREATE NEW USER\n");
                    createNewUser();
                    break;

                case 3:
                    System.out.println("DELETE USER\n");
                    deleteUser();
                    break;

                case 4:
                    System.out.println("READ FILE\n");
                    readFile();
                    break;

                case 9:
                    System.out.println("QUIT\n");
                    quit();
                    keepRunning = true;
                    break;
            }
        }
    }


    // Menu option 1 ----------------------------------------------
    public void viewUserList() {
        for (User user : users) {
            System.out.println(user);
        }
    }

    // Menu option 2 ----------------------------------------------
    public void createNewUser() {
        System.out.println("Please enter");

        // Log user info --------------
        System.out.print("ID: ");
        int ID = readInt();                 // Scans ID number
        System.out.print("\nName: ");
        String name = in.next();            // Scans name
        System.out.print("\nPassword: ");
        String password = in.next();        // Scans Password

        // Create new User based on info attributes and add to list
        User newUser = new User(ID, name, password);            // Instantiate a user based on attributes
        users.add(newUser);                                        // Adds user til ArrayList<User> userList

        saveFile(ID);
    }

    // Menu option 3 ----------------------------------------------
    public void deleteUser() {
        System.out.print("Which User ID do you want to delete?: ");
        int userID = in.nextInt();

        for (User user : users) {
            if (user.getID() == userID) {
                System.out.println("User ID " + user.getID() + " deleted!");
                users.remove(user);
            } else {
                System.out.println("No user with that ID was found in the list");
            }
        }
    }


    // Menu option 4 ----------------------------------------------
    public void saveFile(int ID) {

            for (User user : users) {

                if (user.getID() == ID) {

                    try {
                        StringBuilder sb = new StringBuilder();

                        // Write user information in FILE - USERFILELIST
                        sb.append("\nID: ").append(user.getID());           // String Build ID
                        sb.append("\nName: ").append(user.getName());           // Append next part (name) - to String Builder
                        sb.append("\nPassword: ").append(user.getPassword());   // Append next part (Password) - to String Builder
                        sb.append("\n------------------------------------------"); // Append dashed lines to String Builder
                        sb.append("\n");                                            // Append newline
                        USERLISTFILE.write(sb.toString().getBytes());           // Write the whole String build in file

                    } catch (IOException e) {
                        e.printStackTrace();

                    }
                }
            }
    }

    // Menu option 5 -----------------------------------------------
    public void readFile() {

        try {

            Scanner scan = new Scanner(new File("userlist.txt"));       // The file to scan

            // Scanning each line in file
            while (scan.hasNextLine()) {
                System.out.println(scan.nextLine());        // Printing all elements of the line
            }

            scan.close();       // Closing the file

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Menu option 9 ----------------------------------------------
    public void quit() {
        System.out.println("Programming is shutting down!");
    }




    // integer reader - clearing also buffer against SCANNER BUG ---------------
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
