package coursera.entities;

public class Course {
    private final String courseName;
    private final int totalTime;
    private final int credit;
    private final String instructorName;

    public Course(String courseName, int totalTime, int credit, String instructorName) {
        this.courseName = courseName;
        this.totalTime = totalTime;
        this.credit = credit;
        this.instructorName = instructorName;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getCredit() {
        return credit;
    }

    public String getInstructorName() {
        return instructorName;
    }
}
