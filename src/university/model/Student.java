package university.model;

import java.util.HashMap;
import java.util.Map;
import university.exceptions.StudentAlreadyEnrolledException;
import university.exceptions.CourseFullException;

public abstract class Student extends Person {

    private String department;
    private Map<Course, Double> enrolledCourses;
    private static final int MAX_COURSES = 5;

// student constructor

    public Student(int id, String name, String email, String department) {
        super(id, name, email);
        this.department = department;
        this.enrolledCourses = new HashMap<>();
    }

    public String getDepartment() { return department; }

    public Map<Course, Double> getEnrolledCourses() { return enrolledCourses; }

    // enroll Course
    public void enrollCourse(Course course) throws StudentAlreadyEnrolledException, CourseFullException {
        if (enrolledCourses.size() >= MAX_COURSES) {
            throw new StudentAlreadyEnrolledException("Student has already enrolled in the maximum number of courses.");
        }
        if (enrolledCourses.containsKey(course)) {
            throw new StudentAlreadyEnrolledException("Student is already enrolled in this course: " + course.getCourseName());
        }
        // ✅ Also add this student to the Course's roster
        course.addStudent(this);
        enrolledCourses.put(course, null);
    }

    public void assignGrade(Course course, double grade) {
        if (!enrolledCourses.containsKey(course)) {
            throw new IllegalArgumentException("Student is not enrolled in this course.");
        }
        enrolledCourses.put(course, grade);
    }

    public boolean isEnrolled(Course course) {
        return enrolledCourses.containsKey(course);
    }

    public boolean canRetake(Course course) {
        Double grade = enrolledCourses.get(course);
        return grade != null && grade < 60.0;
    }

    //Calculates GPA as the average of all assigned grades 
    public double calculateGPA() {
        if (enrolledCourses.isEmpty()) return 0.0;

        double total = 0.0;
        int graded = 0;

        for (Double grade : enrolledCourses.values()) {
            if (grade != null) {
                total += convertToGPA(grade);
                graded++;
            }
        }

        return graded == 0 ? 0.0 : total / graded;
    }

    // Converts 0-100 score to 4.0
    private double convertToGPA(double score) {
        if (score >= 90) return 4.0;
        if (score >= 80) return 3.0;
        if (score >= 70) return 2.0;
        if (score >= 60) return 1.0;
        return 0.0;
    }

    //  Returns total credit hours from enrolled courses
    public int getTotalCredits() {
        int total = 0;
        for (Course course : enrolledCourses.keySet()) {
            total += course.getCredits();
        }
        return total;
    }

    // Abstract method each subclass must implement
    public abstract double calculateTuition();
}