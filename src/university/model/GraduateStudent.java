package university.model;

public class GraduateStudent extends Student {

    private static final double PRICE_PER_CREDIT = 800.0;
    private static final double RESEARCH_FEE = 1500.0;

    // GraduateStudent constructor 
    public GraduateStudent(int id, String name, String email, String department) {
        super(id, name, email, department);
    }

    @Override
    public double calculateTuition() {
        return (getTotalCredits() * PRICE_PER_CREDIT) + RESEARCH_FEE;
    }

    @Override
    public String toString() {
        return "GraduateStudent [" +
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