package university.service;

import java.util.HashMap;
import java.util.Map;

import university.model.Student;
import university.model.Course;

public class UniversityManager {

    private Map<Integer, Student> students;
    private Map<Integer, Course> courses;

    public UniversityManager() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
    }

    // Register Student
  
    public void registerStudent(Student student) {

        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student already exists.");
        }

        students.put(student.getId(), student);
    }


    // Create Course
   
    public void createCourse(Course course) {

        if (courses.containsKey(course.getCourseCode())) {
            throw new IllegalArgumentException("Course already exists.");
        }

        courses.put(course.getCourseCode(), course);
    }

     // Enroll Student in Course

    public void enrollStudentInCourse(int studentId, int courseCode) {

        Student student = students.get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        Course course = courses.get(courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course not found.");
        }

        student.enrollCourse(course);
    }

    // assign Grades

    public void assignGrade(int studentId, int courseCode, double grade) {

        Student student = students.get(studentId);
        if (student == null) {
            throw new IllegalArgumentException("Student not found.");
        }

        Course course = courses.get(courseCode);
        if (course == null) {
            throw new IllegalArgumentException("Course not found.");
        }

        student.assignGrade(course, grade);

        //Calculate Average of GPA

    public double calculateAverageGPA() {

        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0;
        int count = 0;

        for (Student student : students.values()) {
            total += student.calculateGPA();
            count++;
        }

        return total / count;
    }

    // Find TOP Student

    public Student findTopStudent() {

        Student topStudent = null;
        double highestGPA = 0.0;

        for (Student student : students.values()) {

            double gpa = student.calculateGPA();

            if (gpa > highestGPA) {
                highestGPA = gpa;
                topStudent = student;
            }
        }

        return topStudent;
    }

    // getters

    public Map<Integer, Student> getStudents() {
        return students;
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }
}
}