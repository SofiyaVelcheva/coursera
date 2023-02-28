package coursera;

import coursera.services.StudentService;
public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        //String path = "csv";
        String path = "html";
        //String[] pins = {"9507141009","9412011005"};
        String[] pins = new String[0];
        int minCredit = 20;
        String startDate = "2019-07-01";
        String endDate = "2019-08-30";
        studentService.getReport(path, pins, minCredit, startDate, endDate);
    }
}
