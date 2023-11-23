import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Display the initial prompt
        System.out.println("Scholarship Reports Generation");
        System.out.println();
        // Create a simple loop to interact with the user until they choose to exit
        while (true) {
            displayMenu();
            // Get user input
            char choice = getUserChoice();

            //displayCurrentDirectory();

            System.out.println();

            // Process the user's choice
            switch (choice) {
                case 'a':
                    System.out.println("Generating Report A...");
                    break;
                case 'b':
                    System.out.println("Generating Report B...");
                    break;
                case 'e':
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
            }

            System.out.println();

        }
    }

    private static void displayMenu() {
        System.out.println("+------------------------------+");
        System.out.println("| Enter 'a' to create report A |");
        System.out.println("| Enter 'b' to create report B |");
        System.out.println("| Enter 'e' to exit            |");
        System.out.println("+------------------------------+");
    }

    private static char getUserChoice() {
        Scanner scnr = new Scanner(System.in);
        System.out.print("Enter your choice: ");

        return  scnr.next().charAt(0);
    }

    
}