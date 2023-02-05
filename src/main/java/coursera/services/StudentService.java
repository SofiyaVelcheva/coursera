package coursera.services;

import coursera.entities.Student;
import coursera.reports.CSVReporter;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    public static Reporter reporter;
    public static final StudentRepository studentRepository = new StudentRepository();

    public void getReportPIN(String filePath, int[] pin) {
        if (filePath.contains("csv")) {
            reporter = new CSVReporter();
        } else {
            // todo for HTML
        }
        List<Student> students = studentRepository.findAllStudentsByPIN(pin);
        reporter.writeReportToFile(filePath, students);
    }
}
