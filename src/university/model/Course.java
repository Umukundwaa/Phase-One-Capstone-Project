package university.model;

import java.util.ArrayList;
import java.util.List;
import university.exceptions.CourseFullException;

public class Course {
    private int courseCode;
    private String courseName;
    private int credits;
    private int maxStudents;
    private List<Student> enrolledStudents;

    public Course(int courseCode, String courseName, int credits, int maxStudents) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.maxStudents = maxStudents;
        this.enrolledStudents = new ArrayList<>();
    }

    public int getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }
    public int getCredits() { return credits; }
    public int getMaxStudents() { return maxStudents; }
    public List<Student> getEnrolledStudents() { return enrolledStudents; }

  
    public void addStudent(Student student) throws CourseFullException {
        if (enrolledStudents.size() >= maxStudents) {
            throw new CourseFullException("Course '" + courseName + "' is full (max: " + maxStudents + ").");
        }
        enrolledStudents.add(student);
    }

    public boolean isFull() {
        return enrolledStudents.size() >= maxStudents;
    }

    @Override
    public String toString() {
        return "Course [code=" + courseCode + ", name='" + courseName + "', credits=" + credits +
                ", enrolled=" + enrolledStudents.size() + "/" + maxStudents + "]";
    }
}