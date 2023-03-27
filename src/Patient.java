import java.time.LocalDate;

public class Patient extends Person {
    private String patientid;

    /**
     * Creating a constructor with a super class and making getters and setters.
     * @param name
     * @param surname
     * @param dob
     * @param m_number
     * @param patientId
     */
    public Patient(String name, String surname, LocalDate dob, String m_number,String patientId){
        super(name,surname,dob,m_number);
        this.patientid = patientId;
    }
    public String getPatientid() {return patientid;}

    public void setPatientid(int patientid)
    {this.patientid = String.valueOf(patientid);}
}