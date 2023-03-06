package coursera.repositories;

import coursera.entities.Course;
import coursera.entities.Student;

import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentRepository implements GlobalRepository {
    private static final String QUERY_ALL_STUDENTS =
            "SELECT sc.student_pin, s.first_name, s.last_name, s.time_created, sum(c.credit) as total_credit " +
                    "FROM students_courses_xref as sc " +
                    "JOIN courses as c ON sc.course_id = c.id " +
                    "JOIN students as s ON sc.student_pin = s.pin ";
    private static final String GROUP_BY = "GROUP BY sc.student_pin ";
    private static final String HAVING_TOTAL_CREDIT = "HAVING total_credit >= ? ";
    private static final String AND_SC_STUDENT_PIN_IN = "AND sc.student_pin IN ";
    private static final String WHERE_SC_COMPLETION_DATE_BETWEEN_AND = "WHERE sc.completion_date BETWEEN ? AND ? ";
    private static final String QUERY_STUDENT_COURSE =
            "SELECT c.name, c.total_time, c.credit, i.first_name, i.last_name " +
                    "FROM students_courses_xref as sc " +
                    "JOIN courses as c ON sc.course_id = c.id " +
                    "JOIN instructors as i ON c.instructor_id = i.id " +
                    "WHERE sc.student_pin = ? " +
                    "AND sc.completion_date BETWEEN ? AND ?";

    public List<Student> findAllStudentsByCredit(int credit, String startDate, String endDate) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement(QUERY_ALL_STUDENTS
                        + WHERE_SC_COMPLETION_DATE_BETWEEN_AND
                        + GROUP_BY
                        + HAVING_TOTAL_CREDIT)) {
            setPrepareStatement(credit, startDate, endDate, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = getStudent(resultSet);
                student.setCourses(getCoursesByStudent(student.getId(), startDate, endDate));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Student> findAllStudentsByPINAndCredit(String[] pins, int credit, String startDate, String endDate) {
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement(QUERY_ALL_STUDENTS
                        + WHERE_SC_COMPLETION_DATE_BETWEEN_AND
                        + GROUP_BY
                        + HAVING_TOTAL_CREDIT
                        + AND_SC_STUDENT_PIN_IN
                        + "(" + getPlaceHolders(pins) + " )")) {
            setPrepareStatement(credit, startDate, endDate, preparedStatement);
            for (int i = 0; i < pins.length; i++) {
                preparedStatement.setString(i + 4, pins[i]);
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Student student = getStudent(resultSet);
                student.setCourses(getCoursesByStudent(student.getId(), startDate, endDate));
                students.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    private static Student getStudent(ResultSet resultSet) throws SQLException {
        String studentPin = resultSet.getString("student_pin");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String timeCreated = new SimpleDateFormat("dd-MM-yyyy").format(resultSet.getDate("time_created"));
        int totalCredit = resultSet.getInt("total_credit");
        return new Student(studentPin, firstName + " " + lastName, timeCreated, totalCredit);
    }

    private static List<Course> getCoursesByStudent(String studentPin, String startDate, String endDate) {
        List<Course> courses = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement(QUERY_STUDENT_COURSE)) {
            preparedStatement.setString(1, studentPin);
            preparedStatement.setDate(2, Date.valueOf(startDate));
            preparedStatement.setDate(3, Date.valueOf(endDate));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String courseName = resultSet.getString("name");
                int totalTime = resultSet.getInt("total_time");
                int credit = resultSet.getInt("credit");
                String instructorFirstName = resultSet.getString("first_name");
                String instructorLastName = resultSet.getString("last_name");
                courses.add(new Course(courseName, totalTime, credit, instructorFirstName + " " + instructorLastName));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return courses;
    }

    private static String getPlaceHolders(String[] pins) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < pins.length; i++) {
            stringBuilder.append("?,");
        }
        return stringBuilder.deleteCharAt(stringBuilder.length() - 1).toString();
    }

    private static void setPrepareStatement(int credit, String start, String end, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setDate(1, Date.valueOf(start));
        preparedStatement.setDate(2, Date.valueOf(end));
        preparedStatement.setInt(3, credit);
    }

}
