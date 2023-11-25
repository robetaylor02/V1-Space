import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class Student {
    public static void generateStudentReports() {
        // Implement code to generate reports available to students
        System.out.println("Student Reports:");
        System.out.println("1. Applicant Report");
        System.out.println("2. Application Status Report");
        System.out.println("3. Scholarship Match Report");
        // Add more student-specific reports as needed

        int reportChoice = ReportEngineTest.getReportChoice();
        generateStudentReport(reportChoice);
    }

    private static void generateStudentReport(int choice) {
        // Implement logic to generate the selected student report

        switch(choice){
            case 1:
                generateApplicantReport();
                break;
            //add other reports
            default:
            System.out.println("Wrong report choice for student.");

        }
    }
    private static void generateApplicantReport(){
        try (BufferedReader personalInfo = new BufferedReader(new FileReader("personal_info.txt"));
             BufferedReader awardedScholarshipsInfo = new BufferedReader(new FileReader("awarded_scholarships.txt"))) {
            
            System.out.println("Enter your name: ");
            String StudentName = ReportEngineTest.scnr.nextLine();//not sure about this

            //read file personal info
            string StudentPersonalInfoLine;
            
            while(StudentPersonalInfoLine = )

             
            }
        }
}