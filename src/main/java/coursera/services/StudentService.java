package coursera.services;

import coursera.entities.Student;
import coursera.reports.CSVReporter;
import coursera.reports.HTMLReporter;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    public static Reporter reporter;
    public static final StudentRepository studentRepository = new StudentRepository();

    public void getReport(String filePath, String[] pins, int minCredit, String startDate, String endDate) {
        if (filePath.contains("csv")) {
            reporter = new CSVReporter();
        } else {
            reporter = new HTMLReporter();
        }
        List<Student> students;
        if (pins.length == 0) {
            students = studentRepository.findAllStudentsByCredit(minCredit, startDate, endDate);
        } else {
            students = studentRepository.findAllStudentsByPINAndCredit(pins, minCredit, startDate, endDate);
        }
        reporter.writeReportToFile(filePath, students);
    }
}
