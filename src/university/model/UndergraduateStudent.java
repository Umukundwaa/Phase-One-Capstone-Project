package university.model;
public class UndergraduateStudent extends Student {

    private static final double FLAT_RATE = 3000.0;

    public UndergraduateStudent(int id, String name, String email) {
        super(id, name, email);
    }


    @Override

    public double calculateTuition() {
        return FLAT_RATE;
    }

    public String toString() {
        return "UndergraduateStudent [" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", totalCredits=" + getTotalCredits() +
                ", tuition= $ " + calculateTuition() +
                ']';
    }

}