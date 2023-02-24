package coursera.entities;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private final String id;
    private final String name;
    private final String timeCreated;
    private int totalCredit;
    private List<Course> courses;

    public Student(String id, String name, String timeCreated, int totalCredit) {
        this.id = id;
        this.name = name;
        this.timeCreated = timeCreated;
        this.totalCredit = totalCredit;
        this.courses = new ArrayList<>();
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public int getTotalCredit() {
        return totalCredit;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
