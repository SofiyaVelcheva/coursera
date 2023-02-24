package coursera.reports;

import coursera.entities.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVReporter implements Reporter {
    @Override
    public void writeReportToFile(String filePath, List<Student> students) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(filePath))) {
            for (Student student : students) {
                bufferedWriter.write(student.getFirstName());
                bufferedWriter.write(" ");
                bufferedWriter.write(student.getLastName());
                bufferedWriter.write(", ");
                bufferedWriter.write(String.valueOf(student.getTotalCredit()));
                bufferedWriter.write(", ");
                bufferedWriter.write(String.valueOf(student.getTimeCreated()));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
