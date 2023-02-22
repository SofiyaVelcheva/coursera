package coursera.repositories;

import coursera.entities.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentRepository implements GlobalRepository {
    private static final String QUERY_ALL_STUDENTS = "SELECT sc.student_pin, s.first_name, s.last_name, s.time_created," +
            "sum(c.credit) as total_credit " +
            "FROM students_courses_xref as sc " +
            "JOIN courses as c ON sc.course_id = c.id " +
            "JOIN students as s ON sc.student_pin = s.pin ";
    private static final String GROUP_BY = "GROUP BY sc.student_pin ";
    private static final String CREDIT_CONSTRAINT = "HAVING total_credit >= ? ";
    private static final String PIN_CONSTRAIN = "AND sc.student_pin IN ";

    public List<Student> findAllStudentsByCredit(int credit) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement(QUERY_ALL_STUDENTS + GROUP_BY + CREDIT_CONSTRAINT)) {
            preparedStatement.setInt(1, credit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(getStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Student> findAllStudentsByPINAndCredit(String[] pins, int credit) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement(QUERY_ALL_STUDENTS
                        + GROUP_BY
                        + CREDIT_CONSTRAINT
                        + PIN_CONSTRAIN
                        + "(" + getPlaceHolders(pins) + " )")) {
            preparedStatement.setInt(1, credit);
            for (int i = 0; i < pins.length; i++) {
                preparedStatement.setString(i + 2, pins[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                students.add(getStudent(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    private static Student getStudent(ResultSet rs) throws SQLException {
        Student student;
        String firstName = rs.getString("first_name");
        String lastname = rs.getString("last_name");
        String timeCreated = new SimpleDateFormat("dd-MM-yyyy").format(rs.getDate("time_created"));
        student = new Student(firstName, lastname, timeCreated);
        return student;
    }

    private static String getPlaceHolders(String[] pins) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pins.length; i++) {
            stringBuilder.append("?,");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }
}
