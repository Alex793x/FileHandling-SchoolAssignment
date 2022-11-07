package Menu;
import java.util.Scanner;


public class Menu {
    private final String menuHeader;
    private final String leadText;
    private final String[] menuItems;

    public Menu(String menuHeader, String leadText, String[] menuItems) {
        this.menuHeader = menuHeader;
        this.leadText = leadText;
        this.menuItems = menuItems;
    }

    public void printMenu() {
        StringBuilder printString = new StringBuilder(menuHeader + "\n");

        for (int i = 0; i < menuItems.length; i++)
            printString.append(menuItems[i]).append("\n");
        System.out.println("\n" + printString);
    }

    public int readChoice() {
            Scanner scanner = new Scanner(System.in);
            boolean validChoice = false;
            int choice = -1;

            while (!validChoice) {
                System.out.print(leadText);
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    validChoice = true;
                } else {
                    scanner.nextLine();
                }
            }
            return choice;
        }
}

