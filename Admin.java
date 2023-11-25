import java.util.Scanner;

public class Admin {
    public static void generateAdminReports() {
        // Implement code to generate reports available to admins
        System.out.println("Admin Reports:");
        System.out.println("1. Scholarship Summary Report");
        System.out.println("2. Reviewable Applications Report");
        System.out.println("3. Disbursement Report");

        int reportChoice = ReportEngineTest.getReportChoice();
        generateAdminReport(reportChoice);
    }

    private static void generateAdminReport(int choice) {
        // Implement logic to generate the selected admin report
        System.out.println("Generating Admin Report " + choice);
    }
}