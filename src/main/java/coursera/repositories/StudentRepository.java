package coursera.repositories;

import coursera.entities.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class StudentRepository implements GlobalRepository {
    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();
        try (ResultSet rs = CONNECTION.createStatement().executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                students.add(getStudent(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }

    public List<Student> findAllStudentsByPIN(String[] pins) { // credit, pin
        List<Student> students = new ArrayList<>();
        try (PreparedStatement preparedStatement = CONNECTION
                .prepareStatement("SELECT * FROM students WHERE pin IN (" + getPlaceHolders(pins) + ")")) {
            for (int i = 0; i < pins.length; i++) {
                preparedStatement.setString(i + 1, pins[i]);
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
