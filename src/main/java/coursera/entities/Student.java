package coursera.entities;

public class Student {
    private final String firstName;
    private final String lastName;
    private final String timeCreated;
    private int totalCredit;

    public Student(String firstName, String lastName, String timeCreated, int totalCredit) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeCreated = timeCreated;
        this.totalCredit = totalCredit;
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

    public int getTotalCredit() {
        return totalCredit;
    }
}
