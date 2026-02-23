package university.model;

public class GraduateStudent extends Student {
    private static final double PRICE_PER_CREDIT = 800.0;
    private static final double RESEARCH_FEE = 1500.0;

    public GraduateStudent( int id, String name , String email){
        super(id, name, email);
    }
    
    @Override

    public double calculateTuition() {
        return (getTotalCredits() * PRICE_PER_CREDIT) + RESEARCH_FEE;
    }

   public String toString() {
        return "GraduateStudent [" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", totalCredits=" + getTotalCredits() +
                ", tuition=" + calculateTuition() +
                ']';
    }
}