package coursera.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import coursera.entities.Student;
import coursera.reports.Reporter;
import coursera.repositories.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StudentServiceTest {
    StudentRepository studentRepository;
    Reporter reporter;
    StudentService studentService;

    @BeforeAll
    public void setup() {
        reporter = mock(Reporter.class);
        studentRepository = mock(StudentRepository.class);
        studentService = new StudentService(studentRepository, reporter);
    }

    @Test
    void GetReport_ByCredit_WithoutStudents() {
        // GIVEN
        when(studentRepository
                .findAllStudentsByCredit(50, "2020-10-08", "2022-10-08"))
                .thenReturn(null);
        // WHEN
        studentService.getReport("html", new String[]{}, 50, "2020-10-08", "2022-10-08");
        // THEN
        verify(reporter).writeReportToFile("html", null);
    }

    @Test
    void GetReport_ByCredit_ReturnException() {
        when(studentRepository
                .findAllStudentsByCredit(20, "2020-01-12", "2019-08-30"))
                .thenThrow(new RuntimeException());
        studentService.getReport("csv", new String[]{}, 20, "2020-01-12", "2019-08-30");
        verify(reporter).writeReportToFile("csv", new ArrayList<>());
    }

    @Test
    void GetReport_ForSpecificStudents() {
        List<Student> students = new ArrayList<>();
        students.add(mock(Student.class));
        when(studentRepository
                .findAllStudentsByPINAndCredit(new String[]{"9507141009", "9412011005"}, 20, "2020-01-12", "2019-08-30"))
                .thenReturn(students);
        studentService.getReport("csv", new String[]{"9507141009", "9412011005"}, 20, "2020-01-12", "2019-08-30");
        verify(reporter).writeReportToFile("csv", students);
    }

    @Test
    void GetReport_ForSpecificStudents_WithEmptyResult() {
        when(studentRepository
                .findAllStudentsByPINAndCredit(new String[]{"9507141009", "9412011005"}, 20, "2020-01-12", "2019-08-30"))
                .thenReturn(new ArrayList<>());
        studentService.getReport("html", new String[]{"9507141009", "9412011005"}, 20, "2020-01-12", "2019-08-30");
        verify(reporter).writeReportToFile("html", new ArrayList<>());
    }
}