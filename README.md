# University Management System
## Java Backend Phase 1 Capstone Project

A console-based University Management System built with Java OOP principles, Collections, Exception Handling, and File Persistence.

---

## Project Structure
```
Phase-One-Capstone-Project/
├── src/
│   └── university/
│       ├── App/
│       │   └── Main.java                  # Console menu entry point
│       ├── model/
│       │   ├── Person.java                # Abstract base class
│       │   ├── Student.java               # Abstract student class
│       │   ├── UndergraduateStudent.java  # Flat rate tuition
│       │   ├── GraduateStudent.java       # Per-credit tuition + research fee
│       │   ├── Instructor.java            # Instructor class
│       │   └── Course.java                # Course with student roster
│       ├── service/
│       │   └── UniversityManager.java     # Central business logic controller
│       ├── exceptions/
│       │   ├── CourseFullException.java
│       │   └── StudentAlreadyEnrolledException.java
│       └── persistence/
│           └── FileManager.java           # File I/O for data persistence
├── data/
│   ├── students.txt                       # Saved student records
│   ├── courses.txt                        # Saved course records
│   └── enrollments.txt                    # Saved enrollment records
└── README.md
```

---

## Features

- Register undergraduate and graduate students
- Create courses with capacity limits
- Enroll students in courses
- Assign grades to students
- View student records with GPA and enrolled courses
- Generate Dean's List for students with GPA > 3.5
- Persistent data storage using file I/O

---

## How to Run

### Prerequisites
- Java JDK 8 or higher
- Git

### Steps

**1. Clone the repository**
```bash
git clone https://github.com/Umukundwaa/Phase-One-Capstone-Project.git
cd Phase-One-Capstone-Project
```

**2. Create output and data directories**
```bash
mkdir out
mkdir data
```

**3. Compile**
```bash
javac -d out -sourcepath src src/university/App/Main.java
```

**4. Run**
```bash
java -cp out university.app.Main
```

---

## OOP Concepts Applied

| Concept | Where Used |
|---|---|
| Inheritance | Student and Instructor extend Person |
| Polymorphism | calculateTuition() overridden in UG and GRAD |
| Encapsulation | Private fields with getters in all classes |
| Abstract Classes | Person and Student are abstract |
| Collections | List<Student> in Course, Map<Course, Double> in Student |

---

## Exception Handling

| Exception | When Thrown |
|---|---|
| CourseFullException | Enrolling in a course that has reached max capacity |
| StudentAlreadyEnrolledException | Enrolling in a course the student is already in |
| IllegalArgumentException | Student or course not found |

---

## GPA Scale

| Score | GPA |
|---|---|
| 90 - 100 | 4.0 |
| 80 - 89  | 3.0 |
| 70 - 79  | 2.0 |
| 60 - 69  | 1.0 |
| Below 60 | 0.0 |

---

## Branch Structure

| Branch | Contains |
|---|---|
| main | Full working application |
| lab1-domain-model | OOP domain model classes |
| lab2-enrollment-engine | Business logic and exceptions |
| lab3 | File persistence and console interface |

---

## Author
**Umukundwa**
Java Backend Development — Phase 1 Capstone
