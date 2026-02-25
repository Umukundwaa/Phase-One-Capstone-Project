package university.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String department;
    private List<Course> assignedCourses;

    public Instructor(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }

    public String getDepartment() { return department; }

    public void assignCourse(Course course) {
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }

    public List<Course> getAssignedCourses() { return assignedCourses; }

    public void assignGrade(Student student, Course course, double grade) {
        if (assignedCourses.contains(course)) {
            student.assignGrade(course, grade);
        } else {
            throw new IllegalArgumentException("Instructor is not assigned to this course.");
        }
    }

    @Override
    public String toString() {
        return "Instructor [id=" + getId() + ", name='" + getName() + "', email='" + getEmail() +
                "', department='" + department + "']";
    }
}