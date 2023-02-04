package coursera.services;

import coursera.entities.Student;
import coursera.reports.CSVReporter;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    public static Reporter reporter;
    public static final StudentRepository studentRepository = new StudentRepository();

    // only business logic

    public void getReportPIN(String filePath, int[] pin) {
        /*
        1. file path which is for csv or HTML
        2. get information from database with Student Repository
        3. write in file
         */

        // *********** 1 ***********
        if (filePath.contains("csv")) {
            reporter = new CSVReporter();
        } else {
            // todo for HTML
        }

        // *********** 2 ***********
        List<Student> students = studentRepository.findAllStudentsByPIN(pin);

        // *********** 3 ***********
        reporter.writeReportToFile(filePath, students);
    }
}
