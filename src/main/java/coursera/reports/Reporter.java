package coursera.reports;


import coursera.entities.Student;

import java.util.List;

public interface Reporter {
    void writeReportToFile(String filePath, List<Student> students);
}
