import java.time.LocalDate;
import java.time.LocalTime;

/**
 * creating variables
 * creating the constructor.
 * creating the getters and the setters.
 */

public class Consultation extends Patient{
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private double cost;
    private String notes;
    private String doctorID;


    public Consultation(String name, String surname, LocalDate dob, String mobile, String patientID,LocalTime startTime, LocalTime endTime, LocalDate consulDate,String doctorID,double cost,String notes) {
        super(name,surname,dob,mobile,patientID);
        this.date = consulDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.doctorID = doctorID;
        this.cost = cost;
        this.notes = notes;
    }


    public LocalDate getDate() {return date;}

    public void setDate(LocalDate date) {this.date = date;}

    public LocalTime getStartTime() {return startTime;}

    public void setStartTime(LocalTime startTime) {this.startTime = startTime;}

    public LocalTime getEndTime() {return endTime;}

    public void setEndTime(LocalTime endTime) {this.endTime = endTime;}

    public double getCost() {return cost;}

    public void setCost(String cost) {this.cost = Double.parseDouble(cost);}

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}

    public String getConsulID() {return doctorID;}

    public void setConsulID(String consulID) {this.doctorID = consulID;}

    /**
     * making a method to print the details of the consultation on the console.
     * @return values
     *
     */
    public String toString(){
        return  "01.Name             : " + this.getName() + '\n' +
                "02.SurName          : " + this.getSurname() + '\n' +
                "03.Date of Birth    : " + this.getDob() + '\n' +
                "04.Mobile Number    : " + this.getM_number() + '\n' +
                "05.Patient ID       : " + this.getPatientid() + '\n' +
                "06.Consulted Doctor : " + doctorID +'\n'+
                "07.Consultation Date: " + date +'\n'+
                "08.Start Time       : " + startTime +'\n'+
                "09.End Time         : " + endTime +'\n'+
                "10.Additional-Note  : " + notes +'\n'+
                "11.Cost            : " + cost;
    }
}

