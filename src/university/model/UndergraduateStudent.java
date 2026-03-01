package university.model;

public class UndergraduateStudent extends Student {

    private static final double FLAT_RATE = 3000.0;

// UndergraduateStudent
    public UndergraduateStudent(int id, String name, String email, String department) {
        super(id, name, email, department);
    }

    @Override
    public double calculateTuition() {
        return FLAT_RATE;
    }

    @Override
    public String toString() {
        return "UndergraduateStudent [" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                ", department='" + getDepartment() + "'" +
                ", totalCredits=" + getTotalCredits() +
                ", GPA=" + String.format("%.2f", calculateGPA()) +
                ", tuition=$" + calculateTuition() +
                "]";
    }
}