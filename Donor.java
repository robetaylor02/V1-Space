import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Donor {
    private static boolean menu = true;

    public static void generateDonorReports(Scanner scnr) {
        while (menu) {
            System.out.println("Donor Report:");
            System.out.println("1. Donor Summary Report");
            System.out.println("4. Go back to main menu ");

            int reportChoice = ReportEngineTest.getReportChoice(scnr);
            if (reportChoice == 4) {
                menu = true;
                return;
            } else if (reportChoice == 1) {
                System.out.println("Would you like to preview this report?: ");
                System.out.println("Enter (Y/N): ");
                String previewChoice = scnr.next();

                if (previewChoice.equalsIgnoreCase("Y")) {
                    generateDonorPreview(scnr);
                }
                generateDonorReport(reportChoice, scnr);
            }
        }
    }

    private static void generateDonorReport(int choice, Scanner scnr) {
        try {
            switch (choice) {
                case 1:
                    generateDonorSummaryReport(scnr);
                    break;
                default:
                    System.out.println("Wrong report choice for donors");
            }
        } catch (IOException e) {
            e.printStackTrace(); // or handle the exception as needed
        }
    }

    private static void generateDonorPreview(Scanner scnr) {
        System.out.println("Preview Generated: ");
        System.out.println("Donor Report: \n");
        System.out.println("===================================\n");
        System.out.println("Scholarship: 'Example Scholarship'\n");
        System.out.println("Status: 'Closed'\n");
        System.out.println("Award Amount: '1000' \n");
        System.out.println("Number of applicants awarded: '10'\n");
        System.out.println("Total amount awarded: '10,000' \n");
        System.out.println("Date closed on:  '01/01/23' \n");
        System.out.println("===================================\n");
    }

    private static void generateDonorSummaryReport(Scanner scnr) throws IOException {
        try (BufferedReader scholarshipsList = new BufferedReader(new FileReader("DonorScholarshipStatus.txt"));
             BufferedWriter donorReportOutputFile = new BufferedWriter(new FileWriter("donor_Report.txt"))) {

            System.out.println("Enter the name of your scholarship: ");
            scnr.nextLine();
            String scholarshipName = scnr.nextLine();

            String scholarshipLine;// read file
            boolean scholarshipFound = false;

            while ((scholarshipLine = scholarshipsList.readLine()) != null) {
                String[] scholarship = scholarshipLine.split(",");
                int awardAmount = Integer.parseInt(scholarship[1]) * Integer.parseInt(scholarship[2]);

                if (scholarship[0].equalsIgnoreCase(scholarshipName.trim()) && scholarship[3].equals("Closed")) {
                    donorReportOutputFile.write("Donor Report: \n");
                    donorReportOutputFile.write("===================================\n");
                    donorReportOutputFile.write("Scholarship: " + scholarship[0] + ":\n");
                    donorReportOutputFile.write("Status: " + scholarship[3] + "\n");
                    donorReportOutputFile.write("Award Amount: " + scholarship[1] + "\n");
                    donorReportOutputFile.write("Number of applicants awarded: " + scholarship[2] + "\n");
                    donorReportOutputFile.write("Total amount awarded: " + awardAmount + "\n");
                    donorReportOutputFile.write("Date closed on:  " + scholarship[4] + "\n");
                    donorReportOutputFile.write("===================================\n");
                    scholarshipFound = true;
                    break;
                } else if (scholarship[0].equalsIgnoreCase(scholarshipName.trim())) {
                    donorReportOutputFile.write("Donor Report: \n");
                    donorReportOutputFile.write("===================================\n");
                    donorReportOutputFile.write("Scholarship: " + scholarship[0] + ":\n");
                    donorReportOutputFile.write("Status: " + scholarship[3] + "\n");
                    donorReportOutputFile.write("Award Amount: " + scholarship[1] + "\n");
                    donorReportOutputFile.write("Number of applicants to be awarded: " + scholarship[2] + "\n");
                    donorReportOutputFile.write("Total amount to be awarded: " + awardAmount + "\n");
                    donorReportOutputFile.write("Date to be closed on:  " + scholarship[4] + "\n");
                    donorReportOutputFile.write("===================================\n");
                    scholarshipFound = true;
                    break;
                }
            }

            if (!scholarshipFound) {
                throw new IllegalArgumentException("Scholarship not found. Please enter a valid name.");
            }

            System.out.println("Donor Summary Report generated and saved to 'donor_Report.txt'");
        }
    }
}
