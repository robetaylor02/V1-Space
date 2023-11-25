import java.util.Scanner;

public class Donor {
    public static void generateDonorReports() {
        // Implement code to generate reports available to donors
        System.out.println("Donor Reports:");
        System.out.println("1. Donor Scholarship Report");
     

        int reportChoice = ReportEngineTest.getReportChoice();
        generateDonorReport(reportChoice);
    }

    private static void generateDonorReport(int choice) {
        // Implement logic to generate the selected donor report
        System.out.println("Generating Donor Report " + choice);
    }
}