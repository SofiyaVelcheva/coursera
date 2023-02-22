package coursera.entities;

import java.util.Date;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String timeCreated;

    public Student(String firstName, String lastName, String timeCreated) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeCreated = timeCreated;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getTimeCreated() {
        return timeCreated;
    }
}
