package coursera.services;

import coursera.entities.Student;
import coursera.reports.CSVReporter;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    public static Reporter reporter;
    public static final StudentRepository studentRepository = new StudentRepository();

    public void getReportPIN(String filePath, String[] pin) {
        if (filePath.contains("csv")) {
            reporter = new CSVReporter();
        } else {
            // todo for HTML
        }
        List<Student> students = new ArrayList<>();
        if (pin.length == 0) {
            students = studentRepository.findAllStudents();
        } else {
            for (String i : pin) {
                students.add(studentRepository.findStudentByPIN(i));
            }
        }
        reporter.writeReportToFile(filePath, students);
    }
}
