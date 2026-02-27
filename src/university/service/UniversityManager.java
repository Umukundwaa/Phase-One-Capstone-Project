package university.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import university.exceptions.CourseFullException;
import university.exceptions.StudentAlreadyEnrolledException;
import university.model.*;
import university.persistence.FileManager;

public class UniversityManager {

    private Map<Integer, Student> students;
    private Map<Integer, Course> courses;
    private FileManager fileManager;

    public UniversityManager() {
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
        this.fileManager = new FileManager();
        loadData();
    }

    public void registerStudent(int id, String name, String email, String department, String type) {
        if (students.containsKey(id)) {
            throw new IllegalArgumentException("Student with ID " + id + " already exists.");
        }
        Student student;
        if (type.equalsIgnoreCase("UG")) {
            student = new UndergraduateStudent(id, name, email, department);
        } else if (type.equalsIgnoreCase("GRAD")) {
            student = new GraduateStudent(id, name, email, department);
        } else {
            throw new IllegalArgumentException("Invalid type. Use 'UG' or 'GRAD'.");
        }
        students.put(id, student);
        saveData();
    }

    public void createCourse(int code, String title, int credits, int capacity) {
        if (courses.containsKey(code)) {
            throw new IllegalArgumentException("Course with code " + code + " already exists.");
        }
        courses.put(code, new Course(code, title, credits, capacity));
        saveData();
    }

    public void enrollStudentInCourse(int studentId, int courseCode)
            throws StudentAlreadyEnrolledException, CourseFullException {
        Student student = students.get(studentId);
        if (student == null) throw new IllegalArgumentException("Student not found.");
        Course course = courses.get(courseCode);
        if (course == null) throw new IllegalArgumentException("Course not found.");
        student.enrollCourse(course);
        saveData();
    }

    public void assignGrade(int studentId, int courseCode, double grade) {
        Student student = students.get(studentId);
        if (student == null) throw new IllegalArgumentException("Student not found.");
        Course course = courses.get(courseCode);
        if (course == null) throw new IllegalArgumentException("Course not found.");
        student.assignGrade(course, grade);
        saveData();
    }

    public void viewStudentRecord(int studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.println("\n===== STUDENT RECORD =====");
        System.out.println(student);
        System.out.println("GPA: " + String.format("%.2f", student.calculateGPA()));
        System.out.println("Enrolled Courses:");
        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("  (No courses enrolled)");
        } else {
            for (Map.Entry<Course, Double> entry : student.getEnrolledCourses().entrySet()) {
                Course c = entry.getKey();
                Double grade = entry.getValue();
                System.out.println("  - " + c.getCourseName() + " (" + c.getCredits() + " credits) | Grade: "
                        + (grade == null ? "Not graded" : grade));
            }
        }
    }

    public void generateDeansList() {
        System.out.println("\n===== DEAN'S LIST (GPA > 3.5) =====");
        boolean found = false;
        for (Student student : students.values()) {
            if (student.calculateGPA() > 3.5) {
                System.out.println("  - " + student.getName() + " | GPA: "
                        + String.format("%.2f", student.calculateGPA())
                        + " | Dept: " + student.getDepartment());
                found = true;
            }
        }
        if (!found) System.out.println("  No students qualify for the Dean's List.");
    }

    public double calculateAverageGPA() {
        if (students.isEmpty()) return 0.0;
        double total = 0;
        for (Student s : students.values()) total += s.calculateGPA();
        return total / students.size();
    }

    public Student findTopStudent() {
        Student top = null;
        double highest = -1;
        for (Student s : students.values()) {
            if (s.calculateGPA() > highest) {
                highest = s.calculateGPA();
                top = s;
            }
        }
        return top;
    }

    public Map<Integer, Student> getStudents() { return students; }
    public Map<Integer, Course> getCourses() { return courses; }

    private void loadData() {
        try {
            java.util.List<Student> loadedStudents = fileManager.loadStudents();
            java.util.List<Course> loadedCourses = fileManager.loadCourses();
            for (Student s : loadedStudents) students.put(s.getId(), s);
            for (Course c : loadedCourses) courses.put(c.getCourseCode(), c);
            fileManager.loadEnrollments(loadedStudents, loadedCourses);
        } catch (IOException | StudentAlreadyEnrolledException | CourseFullException e) {
            System.out.println("Notice: Could not load saved data. Starting fresh. (" + e.getMessage() + ")");
        }
    }

    private void saveData() {
        try {
            fileManager.saveStudents(students.values());
            fileManager.saveCourses(courses.values());
            fileManager.saveEnrollments(students.values());
        } catch (IOException e) {
            System.out.println("Warning: Could not save data. (" + e.getMessage() + ")");
        }
    }
}
