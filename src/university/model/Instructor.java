package university.model;

import java.util.ArrayList;
import java.util.List;

public class Instructor extends Person {
    private String department;

    List<Course> assignedCourses;


    public Instructor(int id, String name, String email, String department){
        super( id, name, email);
        this.department = department;
        this.assignedCourses = new ArrayList<>();
    }
    public String getDepartment() {
        return department;
    }
    public void assignCourse(Course course) {
        if (!assignedCourses.contains(course)) {
            assignedCourses.add(course);
        }
    }
    public List<Course> getAssignedCourses(){
        return assignedCourses;
    }



    @Override
    public String toString() {
        return "Instructor [" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", department='" + department + '\'' +
                ']';
    }
}
