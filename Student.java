import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Student {
    private static boolean menu = true;
    public static void generateStudentReports(Scanner scnr) {
        // Implement code to generate reports available to students
        
        while(menu){
        System.out.println("Student Reports:");
        System.out.println("1. Applicant Report");
        System.out.println("2. Application Status Report");
        System.out.println("3. Scholarship Match Report");
        System.out.println("4.Go back to main menu");

        int reportChoice = ReportEngineTest.getReportChoice(scnr);
        generateStudentReport(reportChoice, scnr);
        
    }
}

    private static void generateStudentReport(int choice, Scanner scnr){

        switch (choice) {
            case 1:
                generateApplicantReport(scnr);
                break;
            // add application status report and scholarship match report
            case 2: 
                genrateApplicationStatusReport(scnr);
                break;
            case 4:
                menu = false;
                break;
            default:
                System.out.println("Wrong report choice for student.");
        }
    }

    private static void generateApplicantReport(Scanner scnr) {
        try (BufferedReader personalInfo = new BufferedReader(new FileReader("personal_info.txt"));
             BufferedReader awardedScholarshipsInfo = new BufferedReader(new FileReader("awarded_scholarships.txt"));
             BufferedWriter applicantReportoutputFile = new BufferedWriter(new FileWriter("applicant_report.txt"))){

            System.out.println("Enter your name: ");
            scnr.nextLine();
            String studentName = scnr.nextLine();

            // read file personal info
            String studentPersonalInfoLine;

            while ((studentPersonalInfoLine = personalInfo.readLine()) != null) {
                String[] student = studentPersonalInfoLine.split(",");
                if (student[0].equalsIgnoreCase(studentName.trim())) {
                    applicantReportoutputFile.write("Applicant Report for " + student[0] + ":\n");
                    applicantReportoutputFile.write("===================================\n");
                    applicantReportoutputFile.write("Name: " + student[0] + "\n");
                    applicantReportoutputFile.write("GPA: " + student[1] + "\n");
                    applicantReportoutputFile.write("Intended Graduation Year: " + student[2] + "\n");
                    applicantReportoutputFile.write("Major: " + student[3] + "\n\n");
                    break;
                }
            }

            applicantReportoutputFile.write("Previously awarded scholarships:\n ");
            String awardedScholarshipsLine;

            while ((awardedScholarshipsLine = awardedScholarshipsInfo.readLine()) != null) {
                String[] scholarships = awardedScholarshipsLine.split(",");
                if (scholarships[0].equalsIgnoreCase(studentName.trim())) {
                    for (int i = 1; i < scholarships.length; i++) {
                        applicantReportoutputFile.write("-" + scholarships[i] + "\n");
                    }
                    break;
                }
            }
            System.out.println("Applicant report generated and saved to 'applicant_report.txt'");
        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    public static void genrateApplicationStatusReport(Scanner scnr){
        try (BufferedReader scholarshipStatus = new BufferedReader(new FileReader("ScholarshipStatus.txt"));
            BufferedWriter applicantStatusReportOutputFile = new BufferedWriter(new FileWriter("applicantstatus_report.txt"))) {

            System.out.println("Enter applicant name: ");
            scnr.nextLine();
            String StudentName = scnr.nextLine();

            String scholarshipline;

            while((scholarshipline = scholarshipStatus.readLine()) != null){
                String[] scholarship = scholarshipline.split(",");
                if(scholarship[0].equalsIgnoreCase(StudentName.trim())){
                    applicantStatusReportOutputFile.write("Application Status Report for " + scholarship[0] + ":\n");
                    applicantStatusReportOutputFile.write("===================================\n");
                    for (int i = 1; i < scholarship.length; i++) {
                        applicantStatusReportOutputFile.write(scholarship[i] + "\n");
                    }
                    break;
                }

            }

            System.out.println("Application status report generated and saved to 'applicantstatus_report.txt'");
        }catch(IOException except){
                except.printStackTrace();
            }     
    }
}
