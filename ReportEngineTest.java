import java.util.Scanner;

public class ReportEngineTest {
    private static Scanner scnr = new Scanner(System.in);
    public static void main(String[] args){
        boolean exit = false;

        while(!exit){
        System.out.println("Choose what user type you are:");
        System.out.println("1. Student");
        System.out.println("2. Reviewer");
        System.out.println("3. Admin");
        System.out.println("4. Donor");
        System.out.println("5.Exit");
        System.out.println("Enter number: ");

        int userChoice = scnr.nextInt();
        scnr.nextLine();

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
            case 5:
                exit = true;
            default:
                System.out.println("Exiting...");
            }
        }
    }
    public static int getReportChoice(Scanner scnr){
        System.out.println("Enter the number of the report you want to generate: ");
            return scnr.nextInt();
    }
}
