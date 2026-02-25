package university.model;

import java.util.HashMap;
import java.util.Map;
import university.exceptions.StudentAlreadyEnrolledException;

public abstract class Student extends Person {
     private int studentId;
     private Double GPA;
     private String department;

     private Map<Course, Double> enrolledCourses;
     private static final int MAX_COURSES = 5;

    public Student(int id, String name, String email, String department, Double GPA ,int studentId) {
        super(id, name, email);
        this.studentId = studentId;
        this.GPA = GPA;
        this.department = department;
        this.enrolledCourses = new HashMap<>();
}

    public int getStudentId() {
        return studentId;
    }
    public Double getGPA() {
        return GPA;
    }
    public String getDepartment() {
        return department;
    }

    // Enroll in a course.
    public void enrollCourse(Course course , double grade){
        if (enrolledCourses.size() >= MAX_COURSES) {
            throw new StudentAlreadyEnrolledException("Student has already enrolled in the maximum number of courses.");
        }
        if (enrolledCourses.containsKey(course)) {

            throw new StudentAlreadyEnrolledException("Student is already enrolled in this course.");
        }

        enrolledCourses.put(course, grade);
        }
        
          // assign grade to the course
        public void assignGrade(Course course, double grade){
            if (enrolledCourses.containsKey(course)) {
                enrolledCourses.put(course, grade);
            } else {
                throw new IllegalArgumentException("Student is not enrolled in this course.");
            }
        }

        // check if student is enrolled in a course
        public boolean isEnrolled(Course course) {
            return enrolledCourses.containsKey(course);
        }

        // check if student can retake course
        public boolean canRetake(Course course){
            Double grade = enrolledCourses.get(course);
            return grade != null && grade < 60.0;

        }
        public Map<Course, Double> getEnrolledCourses(){
            return enrolledCourses;
        }

    }

