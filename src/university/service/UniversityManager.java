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

    // Assign Grade
 
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
    }

    // Calculate Average GPA 
   
    public double calculateAverageGPA() {

        if (students.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (Student student : students.values()) {
            total += student.calculateGPA();
        }

        return total / students.size();
    }

    // Find Top Student

    public Student findTopStudent() {

        Student topStudent = null;
        double highestGPA = 0.0;

        for (Student student : students.values()) {

            double gpa = student.calculateGPA();

            if (topStudent == null || gpa > highestGPA) {
                highestGPA = gpa;
                topStudent = student;
            }
        }

        return topStudent;
    }

    // View Student Record

    public void viewStudentRecord(int studentId) {

        Student student = students.get(studentId);

        if (student == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.println("===== Student Record =====");
        System.out.println("ID: " + student.getId());
        System.out.println("Name: " + student.getName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Department: " + student.getDepartment());
        System.out.println("GPA: " + student.calculateGPA());
    }

    // Generate Dean's List
   
    public void generateDeansList() {

        System.out.println("===== Dean's List =====");

        boolean found = false;

        for (Student student : students.values()) {

            double gpa = student.calculateGPA();

            if (gpa >= 3.5) {
                System.out.println(student.getName() + " - GPA: " + gpa);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No students qualified for Dean's List.");
        }
    }


    public Map<Integer, Student> getStudents() {
        return students;
    }

    public Map<Integer, Course> getCourses() {
        return courses;
    }
}