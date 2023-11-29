import java.util.InputMismatchException;
import java.util.Scanner;

public class ReportEngineTest {
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Choose what user type you are:");
            System.out.println("1. Student");
            System.out.println("2. Reviewer");
            System.out.println("3. Admin");
            System.out.println("4. Donor");
            System.out.println("5. Exit");
            System.out.println("Enter number: ");

            try {
                int userChoice = scnr.nextInt();
                scnr.nextLine(); // Consume the newline character

                if (userChoice >= 1 && userChoice <= 4) {
                    switch (userChoice) {
                        case 1:
                            Student.generateStudentReports(scnr);
                            break;
                        case 2:
                            Reviewer.generateReviewerReports(scnr);
                            break;
                        case 3:
                            Admin.generateAdminReports(scnr);
                            break;
                        case 4:
                            Donor.generateDonorReports(scnr);
                            break;
                    }
                } else if (userChoice == 5) {
                    exit = true;
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                scnr.nextLine(); // Clear the invalid input
            }
        }
    }

    public static int getReportChoice(Scanner scnr) {
        System.out.println("Enter the number of the report you want to generate: ");
        return scnr.nextInt();
    }
}