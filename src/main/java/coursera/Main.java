package coursera;

import coursera.reports.CSVReporter;
import coursera.reports.HTMLReporter;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;
import coursera.services.StudentService;
public class Main {
    private static final StudentRepository STUDENT_REPOSITORY = new StudentRepository();
    public static void main(String[] args) {
        //String path = "csv";
        String path = "html";
        Reporter reporter = path.contains("csv") ? new CSVReporter() : new HTMLReporter();
        StudentService studentService = new StudentService(STUDENT_REPOSITORY, reporter);
        //String path = "html";
        //String[] pins = {"9507141009","9412011005"};
        String[] pins = new String[]{};
        int minCredit = 20;
        String startDate = "2019-07-01";
        String endDate = "2019-08-30";
        studentService.getReport(path, pins, minCredit, startDate, endDate);
    }
}
