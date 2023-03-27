import java.time.LocalDate;

/**
 * creating the vaiables and the constructor with the super class
 */
public class Doctor extends Person {
    private final String licensenum;
    private final String spec;

    public Doctor(String name, String surname, LocalDate dob, String m_number,String licensenum, String spec){
        super(name,surname,dob,m_number);
        this.licensenum = licensenum;
        this.spec = spec;
    }

    public String getLicensenum() {return licensenum;}

    public String getSpec() {return spec;}

    @Override
    public String toString(){
        return " 01. Name               : " + this.getName() + '\n' +
                "02. SurName            : " + this.getSurname() + '\n' +
                "03. Date of Birth      : " + this.getDob() + '\n' +
                "04. Mobile Number      : " + this.getM_number() + '\n' +
                "05. Medical License No : " + this.licensenum + '\n' +
                "06. Specialisation     : " + this.spec + '\n';
    }
}
