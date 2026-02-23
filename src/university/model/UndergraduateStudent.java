package university.model;
public class UndergraduateStudent extends Student {
    public UndergraduateStudent(int id, String name, String email) {
        super(id, name, email);
    }

    @Override

    public String toString() {
        return "UndergraduateStudent [" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ']';
    }

}