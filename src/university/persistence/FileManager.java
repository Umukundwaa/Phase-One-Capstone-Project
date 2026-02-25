package university.persistence;

import university.model.*;
import university.exceptions.CourseFullException;
import university.exceptions.StudentAlreadyEnrolledException;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final String STUDENTS_FILE = "data/students.txt";
    private static final String COURSES_FILE = "data/courses.txt";
    private static final String ENROLLMENTS_FILE = "data/enrollments.txt";

     // SAVE METHODS

    public void saveStudents(Collection<Student> students) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE))) {

            for (Student student : students) {

                String type = student instanceof UndergraduateStudent ? "UG" : "GRAD";

                writer.write(student.getId() + "," +
                        student.getName() + "," +
                        student.getEmail() + "," +
                        student.getDepartment() + "," +
                        type);

                writer.newLine();
            }
        }
    }

    public void saveCourses(Collection<Course> courses) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(COURSES_FILE))) {

            for (Course course : courses) {

                writer.write(course.getCourseCode() + "," +
                        course.getCourseName() + "," +
                        course.getCredits() + "," +
                        course.getMaxStudents());

                writer.newLine();
            }
        }
    }

    public void saveEnrollments(Collection<Student> students) throws IOException {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ENROLLMENTS_FILE))) {

            for (Student student : students) {

                for (Map.Entry<Course, Double> entry : student.getEnrolledCourses().entrySet()) {

                    Course course = entry.getKey();
                    Double grade = entry.getValue();

                    writer.write(student.getId() + "," +
                            course.getCourseCode() + "," +
                            (grade == null ? "null" : grade));

                    writer.newLine();
                }
            }
        }
    }

    // LOAD METHODS

    public List<Student> loadStudents() throws IOException {

        List<Student> students = new ArrayList<>();

        File file = new File(STUDENTS_FILE);
        if (!file.exists()) return students;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String email = parts[2];
                String department = parts[3];
                String type = parts[4];

                Student student;

                if (type.equalsIgnoreCase("UG")) {
                    student = new UndergraduateStudent(id, name, email, department);
                } else {
                    student = new GraduateStudent(id, name, email, department);
                }

                students.add(student);
            }
        }

        return students;
    }

    public List<Course> loadCourses() throws IOException {

        List<Course> courses = new ArrayList<>();

        File file = new File(COURSES_FILE);
        if (!file.exists()) return courses;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int code = Integer.parseInt(parts[0]);
                String name = parts[1];
                int credits = Integer.parseInt(parts[2]);
                int maxStudents = Integer.parseInt(parts[3]);

                courses.add(new Course(code, name, credits, maxStudents));
            }
        }

        return courses;
    }

    public void loadEnrollments(List<Student> students, List<Course> courses)
            throws IOException, StudentAlreadyEnrolledException, CourseFullException {

        File file = new File(ENROLLMENTS_FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",");

                int studentId = Integer.parseInt(parts[0]);
                int courseCode = Integer.parseInt(parts[1]);
                String gradeValue = parts[2];

                Student student = findStudentById(students, studentId);
                Course course = findCourseByCode(courses, courseCode);

                if (student != null && course != null) {

                    student.enrollCourse(course);

                    if (!gradeValue.equals("null")) {
                        student.assignGrade(course, Double.parseDouble(gradeValue));
                    }
                }
            }
        }
    }

    // HELPER METHODS

    private Student findStudentById(List<Student> students, int id) {

        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    private Course findCourseByCode(List<Course> courses, int code) {

        for (Course c : courses) {
            if (c.getCourseCode() == code) return c;
        }
        return null;
    }
}