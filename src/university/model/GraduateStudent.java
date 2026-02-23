package university.model;

public class GraduateStudent extends Student {

    public GraduateStudent( int id, String name , String email){
        super(id, name, email);
    }
    
    @Override
    public String toString() {
        return "GraduateStudent [" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ']';
    }
}