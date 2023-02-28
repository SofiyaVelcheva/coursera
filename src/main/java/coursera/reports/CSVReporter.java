package coursera.reports;

import coursera.entities.Course;
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
            bufferedWriter.write("Student");
            bufferedWriter.write(" - ");
            bufferedWriter.write("Total credit");
            bufferedWriter.write(" - ");
            bufferedWriter.write("Time created");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            for (Student student : students) {
                bufferedWriter.write(student.getName());
                bufferedWriter.write(" - ");
                bufferedWriter.write(String.valueOf(student.getTotalCredit()));
                bufferedWriter.write(" -  ");
                bufferedWriter.write(String.valueOf(student.getTimeCreated()));
                bufferedWriter.newLine();
                bufferedWriter.write("Course name, ");
                bufferedWriter.write("Total time, ");
                bufferedWriter.write("Credit, ");
                bufferedWriter.write("Instructor name");
                bufferedWriter.newLine();
                for (Course course : student.getCourses()) {
                    bufferedWriter.write(course.getCourseName());
                    bufferedWriter.write(", ");
                    bufferedWriter.write(String.valueOf(course.getTotalTime()));
                    bufferedWriter.write(", ");
                    bufferedWriter.write(String.valueOf(course.getCredit()));
                    bufferedWriter.write(", ");
                    bufferedWriter.write(course.getInstructorName());
                    bufferedWriter.newLine();
                }
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
