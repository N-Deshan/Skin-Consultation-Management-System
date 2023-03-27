import java.time.LocalDate;
import java.util.*;

/**
 * creating variables.
 * Creating a constructor with the super.
 * Creating getters and setters.
 */
public class Person{
    private String name;
    private String surname;
    private LocalDate dob;
    private String m_number;

    public Person(String newname, String newsurname, LocalDate newdob, String newm_number){
        this.name = newname;
        this.surname = newsurname;
        this.dob = newdob;
        this.m_number = newm_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getM_number() {
        return m_number;
    }

    public void setM_number(String m_number) {
        this.m_number = m_number;
    }


}
