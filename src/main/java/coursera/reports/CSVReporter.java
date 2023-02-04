package coursera.reports;

import coursera.entities.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVReporter implements Reporter {
    @Override
    public void writeReportToFile(String filePath, List<Student> students){

        try(BufferedWriter br = new BufferedWriter(new FileWriter(filePath))){
            for (Student student : students) {
                br.write(student.getFirstName());
                br.write(" ");
                br.write(student.getLastName());
                br.write(", ");
                br.write(String.valueOf(student.getTimeCreated()));
                br.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
