import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Student {
    private static boolean menu = true;

    public static void generateStudentReports(Scanner scnr) {
        while (menu) {
            System.out.println("===================================");
            System.out.println("Student Reports:");
            System.out.println("1. Applicant Report");
            System.out.println("2. Application Status Report");
            System.out.println("3. Applicant Matching Report");
            System.out.println("4. Go back to main menu");

            int reportChoice = getReportChoice(scnr);
            if (reportChoice == 4) {
                menu = true;
                return;
            }

            try {
                generateStudentReport(reportChoice, scnr);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void generateStudentReport(int choice, Scanner scnr) {
        switch (choice) {
            case 1:
                generateApplicantReport(scnr);
                break;
            case 2:
                generateApplicationStatusReport(scnr);
                break;
            case 3:
                generateApplicantMatchingReport(scnr);
                break;
            default:
                throw new IllegalArgumentException("Wrong report choice for student.");
        }
    }

    private static int getReportChoice(Scanner scnr) {
        int choice = -1;
        while (choice < 1 || choice > 4) {
            System.out.println("Enter the number of the report you want to generate: ");
            try {
                choice = Integer.parseInt(scnr.nextLine());
                if (choice < 1 || choice > 4) {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
        return choice;
    }

    private static void generateApplicantReport(Scanner scnr) {
        try (BufferedReader personalInfo = new BufferedReader(new FileReader("personal_info.txt"));
             BufferedReader awardedScholarshipsInfo = new BufferedReader(new FileReader("awarded_scholarships.txt"));
             BufferedWriter applicantReportOutputFile = new BufferedWriter(new FileWriter("applicant_report.txt"))) {

            System.out.println("Enter student name: ");
            String studentName = scnr.nextLine();

            String studentPersonalInfoLine;
            boolean studentFound = false;

            while ((studentPersonalInfoLine = personalInfo.readLine()) != null) {
                String[] student = studentPersonalInfoLine.split(",");
                if (student[0].equalsIgnoreCase(studentName.trim())) {
                    applicantReportOutputFile.write("Applicant Report for " + student[0] + ":\n");
                    applicantReportOutputFile.write("===================================\n");
                    applicantReportOutputFile.write("Name: " + student[0] + "\n");
                    applicantReportOutputFile.write("GPA: " + student[1] + "\n");
                    applicantReportOutputFile.write("Intended Graduation Year: " + student[2] + "\n");
                    applicantReportOutputFile.write("Major: " + student[3] + "\n\n");

                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                throw new IllegalArgumentException("Student not found. Please enter a valid name.");
            }

            // Improved formatting for the report
            applicantReportOutputFile.write("\n\n===================================\n");
            applicantReportOutputFile.write("Previously awarded scholarships:\n");
            applicantReportOutputFile.write("===================================\n");

            String awardedScholarshipsLine;

            while ((awardedScholarshipsLine = awardedScholarshipsInfo.readLine()) != null) {
                String[] scholarships = awardedScholarshipsLine.split(",");
                if (scholarships[0].equalsIgnoreCase(studentName.trim())) {
                    for (int i = 1; i < scholarships.length; i++) {
                        applicantReportOutputFile.write("- " + scholarships[i] + "\n");
                    }
                    break;
                }
            }
            System.out.println("===================================");
            System.out.println("personal_info.txt and awarded_scholarships.txt read correctly.");
            System.out.println("Applicant report generated and saved to 'applicant_report.txt'");
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    private static void generateApplicationStatusReport(Scanner scnr) {
        try (BufferedReader scholarshipStatus = new BufferedReader(new FileReader("ScholarshipStatus.txt"));
             BufferedWriter applicantStatusReportOutputFile = new BufferedWriter(new FileWriter("applicantstatus_report.txt"))) {

            System.out.println("Enter student name: ");
            String studentName = scnr.nextLine();

            String scholarshipLine;
            boolean studentFound = false;

            while ((scholarshipLine = scholarshipStatus.readLine()) != null) {
                String[] scholarship = scholarshipLine.split(",");
                if (scholarship[0].equalsIgnoreCase(studentName.trim())) {
                    applicantStatusReportOutputFile.write("===================================\n");
                    applicantStatusReportOutputFile.write("Application Status Report for " + scholarship[0] + ":\n");
                    applicantStatusReportOutputFile.write("===================================\n");
                    for (int i = 1; i < scholarship.length; i++) {
                        applicantStatusReportOutputFile.write(scholarship[i] + "\n");
                    }
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                throw new IllegalArgumentException("Student not found. Please enter a valid name.");
            }

            System.out.println("===================================");
            System.out.println("ScholarshipStatus.txt read correctly");
            System.out.println("Application status report generated and saved to 'applicantstatus_report.txt'");
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    private static void generateApplicantMatchingReport(Scanner scnr) {
        try (BufferedReader personalInfo = new BufferedReader(new FileReader("personal_info.txt"));
             BufferedReader scholarshipData = new BufferedReader(new FileReader("ScholarshipData.txt"));
             BufferedWriter applicantMatchingReportOutputFile = new BufferedWriter(new FileWriter("applicantmatching_report.txt"))) {

            System.out.println("Enter student name: ");
            String studentName = scnr.nextLine();

            String studentPersonalInfoLine;
            boolean studentFound = false;

            while ((studentPersonalInfoLine = personalInfo.readLine()) != null) {
                String[] student = studentPersonalInfoLine.split(",");
                if (student[0].equalsIgnoreCase(studentName.trim())) {
                    applicantMatchingReportOutputFile.write("Applicant Matching Report for " + student[0] + ":\n");
                    applicantMatchingReportOutputFile.write("===================================\n");
                    applicantMatchingReportOutputFile.write("Name: " + student[0] + "\n");
                    applicantMatchingReportOutputFile.write("GPA: " + student[1] + "\n");
                    applicantMatchingReportOutputFile.write("Major: " + student[3] + "\n\n");

                    String scholarshipMatchingLine;

                    while ((scholarshipMatchingLine = scholarshipData.readLine()) != null) {
                        String[] scholarship = scholarshipMatchingLine.split("\\|");
                        if (scholarship.length == 6) {
                            String scholarshipMajor = scholarship[3].trim();
                            double scholarshipMinGPA = Double.parseDouble(scholarship[4].trim());
                            double studentGPA = Double.parseDouble(student[1].trim());

                            if ((scholarshipMajor.equalsIgnoreCase(student[3].trim()) && studentGPA >= scholarshipMinGPA)) {
                                applicantMatchingReportOutputFile.write("- " + scholarship[0] + "\n");
                            }
                        }
                    }
                    System.out.println("===================================");
                    System.out.println("Personal_info.txt and ScholarshipData.txt read correctly");
                    System.out.println("Applicant matching report generated and saved to 'applicantmatching_report.txt'");
                    studentFound = true;
                    break;
                }
            }

            if (!studentFound) {
                throw new IllegalArgumentException("Student not found. Please enter a valid name.");
            }

        } catch (IOException except) {
            except.printStackTrace();
        }
    }
}