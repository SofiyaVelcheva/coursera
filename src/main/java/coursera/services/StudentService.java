package coursera.services;

import coursera.entities.Student;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;

import java.util.List;

public class StudentService {
    private Reporter reporter;
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository, Reporter reporter){
        this.studentRepository = studentRepository;
        this.reporter = reporter;
    }
    public void getReport(String filePath, String[] pins, int minCredit, String startDate, String endDate) {
        List<Student> students;
        if (pins.length == 0) {
            students = studentRepository.findAllStudentsByCredit(minCredit, startDate, endDate);
        } else {
            students = studentRepository.findAllStudentsByPINAndCredit(pins, minCredit, startDate, endDate);
        }
        reporter.writeReportToFile(filePath, students);
    }
}
