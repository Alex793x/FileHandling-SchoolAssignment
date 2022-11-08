package logginthoseactions;

import Menu.Menu;
import myfriends.UI;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class LoggingThoseActions {

    // Utilities -----------------------------------------
    UI ui = new UI();

    // Attributes -----------------------------------------
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd:MM:yyyy HH:mm:ss");
    String formatDateTime;
    ArrayList<String> lines;
    Menu myMenu = new Menu("MENU", "Please choose option: ", new String[] {
            "1: Add lines", "2: View Lines", "3: Delete lines", "9: Exit logging"
    });

    // FILE HANDLER ---------------------------------------
    FileOutputStream LOGGINGFILES;
    {
        try {
            LOGGINGFILES = new FileOutputStream("logging.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Constructor -----------------------------------------
    public LoggingThoseActions() {
        lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        formatDateTime = LocalDateTime.now().format(format);
        sb.append(formatDateTime).append(": STARTING PROGRAM\n");
        actionLogging(sb);
        mainMenu();
    }

    // Behaviors (Methods) ---------------------------------
    // LoggingMethod ------------------
    private void actionLogging(StringBuilder sb) {
        if (sb != null) {
            try {
                LOGGINGFILES.write(sb.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Menu Option 1 -------------------
    private StringBuilder addLines() {
        String input = "\"" + ui.readLine() + "\"";
        lines.add(input);
        StringBuilder sb = new StringBuilder();
        formatDateTime = LocalDateTime.now().format(format);
        return sb.append(formatDateTime).append(": ").append("Adding new line:  ").append(input).append("\n");
    }

    // Menu Option 2 -------------------
    private StringBuilder viewLines() {
        // Printing out all lines added written in the list
        for (int i = 0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        // Appending to our StringBuilding action happened.
        StringBuilder sb = new StringBuilder();
        formatDateTime = LocalDateTime.now().format(format);
        return sb.append(formatDateTime).append(": Viewing all lines\n");
    }

    // Menu Option 3 -------------------
    private StringBuilder deleteLines() {
        System.out.println("Which element entry do you want to delete?: ");
        int elementChoice = ui.readInt();
        for (int i = 0; i < lines.size(); i++) {
            if ((elementChoice - 1) == i) {
                String input = lines.get(i);
                System.out.println("Element " + (i + 1) + " has been removed");
                lines.remove(i);
                StringBuilder sb = new StringBuilder();
                formatDateTime = LocalDateTime.now().format(format);
               return sb.append(formatDateTime).append(": Deleting line: #").append(i+1).append(" ").append(input).append("\n");
            }
        }
        System.out.println("No elements where found from your entry! ");
        return null;
    }

    // Menu Option 9 -------------------
    private StringBuilder exitLogging() {
        System.out.println("Logging shutting down");
        StringBuilder sb = new StringBuilder();
        formatDateTime = LocalDateTime.now().format(format);
        return sb.append(formatDateTime).append(": TERMINATING PROGRAM\n");
    }

    // Menu RUNNER ---------------------
    private void mainMenu() {
        boolean keepRunning = false;
        while (!keepRunning) {
            myMenu.printMenu();
            switch (myMenu.readChoice()) {
                case 1:
                    System.out.println("ADD LINES");
                    actionLogging(addLines());
                    break;
                case 2:
                    System.out.println("VIEW LINES");
                    actionLogging(viewLines());
                    break;
                case 3:
                    System.out.println("DELETE LINES");
                    actionLogging(deleteLines());
                    break;
                case 9:
                    System.out.println("EXIT LOGGING");
                    actionLogging(exitLogging());
                    keepRunning = true;
                    break;
            }
        }
    }
}
