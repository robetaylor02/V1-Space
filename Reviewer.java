import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class Reviewer {
    private static boolean menu = true;
    public static void generateReviewerReports(Scanner scnr) {
       
        while(menu){
        System.out.println("Reviewer Reports:");
        System.out.println("1. Applicant Report");
        System.out.println("2. Reviewable Applications Report");
        System.out.println("3. Scholarship Summary Report");
        System.out.println("4. Go back to main menu ");
       
        int reportChoice = ReportEngineTest.getReportChoice(scnr);
        generateReviewerReport(reportChoice,scnr);
    }
}

    private static void generateReviewerReport(int choice, Scanner scnr) {

        switch(choice){
            case 1:
                generateReviewerApplicantReport(scnr);
                break;
            case 2:
                generateReviewableApplicationsReport(scnr);
                break;
            case 3:
                generateScholarshipSummaryReport(scnr);
                break;
            case 4:
                menu = false;
                break;
            default:
                System.out.println("Wrong report choice for reviewers");

            
            }
        }

    public static void generateReviewerApplicantReport(Scanner scnr){
        try (BufferedReader personalInfo = new BufferedReader(new FileReader("personal_info.txt"));
        BufferedReader awardedScholarshipsInfo = new BufferedReader(new FileReader("awarded_scholarships.txt"));
        BufferedWriter applicantReportoutputFile = new BufferedWriter(new FileWriter("reviewer_applicant_report.txt"))){

       System.out.println("Enter name of applicant: ");
       scnr.nextLine();
       String studentName = scnr.nextLine();

       String studentPersonalInfoLine;//read file

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
       System.out.println("Applicant report generated and saved to 'reviewer_applicant_report.txt'");

   } catch (IOException except) {
       except.printStackTrace();
   }


    }

    public static void generateReviewableApplicationsReport(Scanner scnr) {
        try (BufferedReader reviewerScholarships = new BufferedReader(new FileReader("ReviewerScholarships.txt"));
             BufferedWriter scholarshipsToReviewOutputFile = new BufferedWriter(new FileWriter("ScholarshipsToReview.txt"))) {
    
            System.out.println("Enter applicant name to see reviewable scholarships: ");
            scnr.nextLine();
            String studentName = scnr.nextLine();
    
            String line;
            while ((line = reviewerScholarships.readLine()) != null) {
                String[] review = line.split(",");
                if (review.length >= 2 && review[0].equalsIgnoreCase(studentName.trim())) {
                    scholarshipsToReviewOutputFile.write("Scholarships to be reviewed for " + studentName + ":" + "\n");
    
                    for (int i = 1; i < review.length; i++) {
                        String[] scholarshipInfo = review[i].split(":");
                        if (scholarshipInfo.length == 2) {
                            String scholarshipName = scholarshipInfo[0].trim();
                            String status = scholarshipInfo[1].trim();
    
                            if (status.equalsIgnoreCase("Submitted")) {
                                scholarshipsToReviewOutputFile.write("- " + scholarshipName + "\n");
                                //scholarshipsToReviewOutputFile.write(studentName + "," + scholarshipName + "\n");
                            }
                        }
                    }
                   //break;
                }
            }
            System.out.println("Scholarships for " + studentName + " written to ScholarshipsToReview.txt");

        } catch (IOException except) {
            except.printStackTrace();
        }
    }

    public static void generateScholarshipSummaryReport(Scanner scnr){

    }

    }
