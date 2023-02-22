package coursera;

import coursera.services.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();
        String path = "csv";
        String[] pins = {"9507141009","9412011005"};
        //String[] pins = new String[0];
        studentService.getReport(path, pins);
    }
}
