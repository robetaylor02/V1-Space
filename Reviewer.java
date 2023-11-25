import java.util.Scanner;

public class Reviewer {
    public static void generateReviewerReports() {
        // Implement code to generate reports available to reviewers
        System.out.println("Reviewer Reports:");
        System.out.println("1. Applicant Report");
        System.out.println("2. Reviewable Applications Report");
       
        int reportChoice = ReportEngineTest.getReportChoice();
        generateReviewerReport(reportChoice);
    }

    private static void generateReviewerReport(int choice) {
        // Implement logic to generate the selected reviewer report
        System.out.println("Generating Reviewer Report " + choice);
    }
}