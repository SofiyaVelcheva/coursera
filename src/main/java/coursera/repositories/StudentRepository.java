package coursera.repositories;

import coursera.entities.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository implements GlobalRepository {

    public List<Student> findAllStudentsByPIN(int[] pin) {
        List<Student> allStudent = new ArrayList<>();
        // statement resultSet global for every
        try (ResultSet rs = c.createStatement().executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date timeCreated = rs.getTime("time_created");
                allStudent.add(new Student(firstName, lastName, timeCreated));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return allStudent;
    }
}
