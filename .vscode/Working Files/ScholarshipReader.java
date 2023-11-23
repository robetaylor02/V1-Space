import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Scholarship {
    String name;
    int awardAmount;
    int numberOfAvailableAwards;
    String major;
    double gpaMinimum;
    Date dueDate;

    public Scholarship(String name, int awardAmount, int numberOfAvailableAwards,
                       String major, double gpaMinimum, Date dueDate) {
        this.name = name;
        this.awardAmount = awardAmount;
        this.numberOfAvailableAwards = numberOfAvailableAwards;
        this.major = major;
        this.gpaMinimum = gpaMinimum;
        this.dueDate = dueDate;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yy");
        return  name + ", Award Amount: " + awardAmount +
            ", Available Awards: " + numberOfAvailableAwards + 
            ", Preferred Major: " + major + '\'' + ", Minimum GPA: " + gpaMinimum +
            ", Due Date: " + dateFormat.format(dueDate);
    }
}



public class ScholarshipReader {

    public static Scholarship[] readScholarships(String filePath) {
        Scholarship[] scholarships = new Scholarship[5]; // Assuming 5 entries for this example

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int index = 0;
            reader.readLine();
            // Skip the header line

            while ((line = reader.readLine()) != null && index < 5) {
                String[] values = line.split(",");
                String name = values[0];
                int awardAmount = Integer.parseInt(values[1]);
                int numberOfAvailableAwards = Integer.parseInt(values[2]);
                String major = values[3];
                double gpaMinimum = Double.parseDouble(values[4]);
                Date dueDate = new SimpleDateFormat("MM/dd/yy").parse(values[5]);

                scholarships[index] = new Scholarship(name, awardAmount, numberOfAvailableAwards, major, gpaMinimum, dueDate);
                index++;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
        return scholarships;
    }


public static void main(String[] args) {
        String inputFilePath = "scholarships_Data.csv"; // Replace with the actual path to your CSV file
        String outputFilePath = "Report_X.txt"; // Replace with the desired output file path

        Scholarship[] scholarships = readScholarships(inputFilePath);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            // Write the scholarships to the output file
            for (Scholarship scholarship : scholarships) {
                writer.write(scholarship.toString());
                writer.newLine();
            }

            System.out.println("Scholarship information written to: " + outputFilePath);
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception according to your needs
        }
    }
}
