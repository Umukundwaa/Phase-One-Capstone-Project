package university.app;

import university.service.UniversityManager;
import university.exceptions.CourseFullException;
import university.exceptions.StudentAlreadyEnrolledException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UniversityManager manager = new UniversityManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n===== UNIVERSITY SYSTEM =====");
            System.out.println("1. Register Student");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll Student in Course");
            System.out.println("4. View Student Record");
            System.out.println("5. Generate Dean's List");
            System.out.println("6. Exit");
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

                        
                        manager.registerStudent(student);
                        System.out.println("Student registered successfully.");
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

                        manager.createCourse(course);
                        System.out.println("Course created successfully.");
                        break;

                    case 3:
                        System.out.print("Enter Student ID: ");
                        int studentId = scanner.nextInt();

                        System.out.print("Enter Course Code: ");
                        int courseCode = scanner.nextInt();
                        scanner.nextLine();

                        manager.enrollStudentInCourse(studentId, courseCode);
                        System.out.println("Enrollment successful.");
                        break;

                    case 4:
                        System.out.print("Enter Student ID: ");
                        int viewId = scanner.nextInt();
                        scanner.nextLine();

                        manager.viewStudentRecord(viewId);
                        break;

                    case 5:
                        manager.generateDeansList();
                        break;

                    case 6:
                        running = false;
                        System.out.println("Exiting system...");
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1-6.");
                }

            } catch (StudentAlreadyEnrolledException | CourseFullException e) {
                System.out.println("Enrollment Error: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Input Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
