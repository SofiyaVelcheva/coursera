package coursera.repositories;

import coursera.entities.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentRepository implements GlobalRepository {

    public Student findStudentByPIN(String pin) {
        Student student = null;
        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM students WHERE pin = ?")) {
            ps.setString(1, pin);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                Date timeCreated = rs.getTime("time_created");
                student = new Student(firstName, lastname, timeCreated);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return student;
    }

    public List<Student> findAllStudents() {
        List<Student> students = new ArrayList<>();
        try (ResultSet rs = c.createStatement().executeQuery("SELECT * FROM students")) {
            while (rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date timeCreated = rs.getTime("time_created");
                students.add(new Student(firstName, lastName, timeCreated));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return students;
    }
}
