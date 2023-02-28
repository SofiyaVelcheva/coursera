package coursera.reports;

import coursera.entities.Course;
import coursera.entities.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLReporter implements Reporter {
    @Override
    public void writeReportToFile(String filePath, List<Student> students) {
        try (BufferedWriter bufferedWriter
                     = new BufferedWriter(new FileWriter(filePath + ".html"))) {
            bufferedWriter.write("<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "border:1px solid black;\n" +
                    "}\n" +
                    "</style>\n" +
                    "<body>\n" +
                    "\n" +
                    "<table style=\"width:100%\">\n" +
                    "<tr style='background-color:#EA8FEA'>\n" +
                    "<th>Student</th>\n" +
                    "<th>Total credit</th>\n" +
                    "<th>Time created</th>\n" +
                    "<th></th>\n" +
                    "<th></th>\n" +
                    "</tr>\n" +
                    "<tr style='background-color:#EA8FEA'>\n" +
                    "<th></th>\n" +
                    "<th>Time</th>\n" +
                    "<th>Course name</th>\n" +
                    "<th>Credit</th>\n" +
                    "<th>Instructor</th>\n" +
                    "</tr>");
            for (Student student : students) {
                bufferedWriter.write("<tr style='background-color:#FFAACF'>\n");
                bufferedWriter.write("<td>" + student.getName() + "</td>\n");
                bufferedWriter.write("<td>" + student.getTotalCredit() + "</td>\n");
                bufferedWriter.write("<td>" + student.getTimeCreated() + "</td>\n");
                bufferedWriter.write("<td></td>\n");
                bufferedWriter.write("<td></td>\n");
                bufferedWriter.write("</tr>\n");
                for (Course course : student.getCourses()) {
                    bufferedWriter.write("<tr style='background-color:#F6E6C2'>\n");
                    bufferedWriter.write("<td></td>\n");
                    bufferedWriter.write("<td>" + course.getCourseName() + "</td>\n");
                    bufferedWriter.write("<td>" + course.getTotalTime() + "</td>\n");
                    bufferedWriter.write("<td>" + course.getCredit() + "</td>\n");
                    bufferedWriter.write("<td>" + course.getInstructorName() + "</td>\n");
                    bufferedWriter.write("</tr>\n");
                }
            }
            bufferedWriter.write("""
                    </table>
                    </body>
                    </html>""");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
