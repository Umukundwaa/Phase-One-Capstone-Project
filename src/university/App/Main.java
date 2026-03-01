package university.app;

import university.service.UniversityManager;
import university.exceptions.CourseFullException;
import university.exceptions.StudentAlreadyEnrolledException;
import university.model.Student;
import university.model.Course;

import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("      UNIVERSITY SYSTEM       ");
            System.out.println("==============================");
            System.out.println("  1. Register Student");
            System.out.println("  2. Create Course");
            System.out.println("  3. Enroll Student in Course");
            System.out.println("  4. View Student Record");
            System.out.println("  5. Generate Dean's List");
            System.out.println("  6. Assign Grade");
            System.out.println("  7. Exit");
            System.out.println("==============================");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1:
                        System.out.print("Enter ID: ");
                        int id = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String dept = scanner.nextLine();
                        System.out.print("Type (UG / GRAD): ");
                        String type = scanner.nextLine();
                        manager.registerStudent(id, name, email, dept, type);
                        System.out.println(">> Student registered successfully.");
                        break;

                    case 2:
                        System.out.print("Enter Course Code: ");
                        int code = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Title: ");
                        String title = scanner.nextLine();
                        System.out.print("Enter Credits: ");
                        int credits = scanner.nextInt();
                        System.out.print("Enter Max Capacity: ");
                        int capacity = scanner.nextInt();
                        scanner.nextLine();
                        manager.createCourse(code, title, credits, capacity);
                        System.out.println(">> Course created successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();
                        System.out.print("Enter Course Code: ");
                        int courseCode = scanner.nextInt();
                        scanner.nextLine();
                        manager.enrollStudentInCourse(studentId, courseCode);
                        System.out.println(">> Enrollment successful.");
                        break;

                    case 4:
                        System.out.print("Enter Student ID: ");
                        int viewId = scanner.nextInt();
                        scanner.nextLine();
                        printStudentRecord(manager, viewId);
                        break;

                    case 5:
                        printDeansList(manager);
                        break;

                    case 6:
                        System.out.print("Enter Student ID: ");
                        int gradeStudentId = scanner.nextInt();
                        System.out.print("Enter Course Code: ");
                        int gradeCourseCode = scanner.nextInt();
                        System.out.print("Enter Grade (0-100): ");
                        double grade = scanner.nextDouble();
                        scanner.nextLine();
                        manager.assignGrade(gradeStudentId, gradeCourseCode, grade);
                        System.out.println(">> Grade assigned successfully.");
                        break;

                    case 7:
                        running = false;
                        System.out.println(">> Goodbye! Data saved.");
                        break;

                    default:
                        System.out.println(">> Invalid choice. Please enter 1-7.");
                }

            } catch (StudentAlreadyEnrolledException | CourseFullException e) {
                System.out.println(">> Enrollment Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(">> Input Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println(">> Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // Print student record
    private static void printStudentRecord(UniversityManager manager, int studentId) {
        Student student = manager.getStudents().get(studentId);
        if (student == null) {
            System.out.println(">> Student not found.");
            return;
        }

        System.out.println("\n------------------------------");
        System.out.println("       STUDENT RECORD         ");
        System.out.println("------------------------------");
        System.out.printf("  %-12s : %s%n",    "ID",         student.getId());
        System.out.printf("  %-12s : %s%n",    "Name",       student.getName());
        System.out.printf("  %-12s : %s%n",    "Email",      student.getEmail());
        System.out.printf("  %-12s : %s%n",    "Department", student.getDepartment());
        System.out.printf("  %-12s : %s%n",    "Credits",    student.getTotalCredits());
        System.out.printf("  %-12s : %.2f%n",  "GPA",        student.calculateGPA());
        System.out.printf("  %-12s : $%.2f%n", "Tuition",    student.calculateTuition());
        System.out.println("------------------------------");

        // Print enrolled courses
        System.out.println("  ENROLLED COURSES");
        System.out.println("------------------------------");
        System.out.printf("  %-8s %-20s %-8s %-10s%n", "Code", "Course", "Credits", "Grade");
        System.out.println("  ----------------------------------------");

        if (student.getEnrolledCourses().isEmpty()) {
            System.out.println("  No courses enrolled yet.");
        } else {
            for (Map.Entry<Course, Double> entry : student.getEnrolledCourses().entrySet()) {
                Course c = entry.getKey();
                Double g = entry.getValue();
                String gradeStr = (g == null) ? "N/A" : String.format("%.1f", g);
                System.out.printf("  %-8d %-20s %-8d %-10s%n",
                        c.getCourseCode(), c.getCourseName(), c.getCredits(), gradeStr);
            }
        }
        System.out.println("------------------------------");
    }

    // Print dean's list
    private static void printDeansList(UniversityManager manager) {
        System.out.println("\n------------------------------");
        System.out.println("        DEAN'S LIST           ");
        System.out.println("   Students with GPA > 3.5    ");
        System.out.println("------------------------------");
        System.out.printf("  %-6s %-20s %-15s %-5s%n", "ID", "Name", "Department", "GPA");
        System.out.println("  --------------------------------------------------");

        boolean found = false;
        for (Student student : manager.getStudents().values()) {
            if (student.calculateGPA() > 3.5) {
                System.out.printf("  %-6d %-20s %-15s %.2f%n",
                        student.getId(),
                        student.getName(),
                        student.getDepartment(),
                        student.calculateGPA());
                found = true;
            }
        }

        if (!found) {
            System.out.println("  No students qualify yet.");
        }
        System.out.println("------------------------------");
    }
}
