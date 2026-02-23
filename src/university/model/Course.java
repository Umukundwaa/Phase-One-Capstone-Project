package university.model;

import java.util.List;
import java.util.ArrayList;
import university.exception.CourseFullException;
public class Course {
    private int courseCode;
    private String courseName;
    private int credits;
    private int maxSudents;
    List<Student> enrolledStudents;

    public Course(int courseCode, String courseName, int credits, int maxSudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxSudents = maxSudents;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseCode() {
        return courseCode;
    }
    
    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxStudents() {
        return maxSudents;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public void addStudent(Student student){
        if (enrolledStudents.size() >= maxSudents) {
            throw new CourseFullException("Maximum number of students enrolled in this course.");
        }
        enrolledStudents.add(student);
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxSudents;
    }
}