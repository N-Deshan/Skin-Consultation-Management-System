import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *Creating a scanner
 * Creating array list(doctorArray)
 * creating array list(consultationArray)

 */
public class WestminsterSkinConsultationManager implements SkinConsultationManager {

    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Doctor> doctorArray = new ArrayList<Doctor>();
    public static ArrayList<Consultation> consultationArray = new ArrayList<>();

    /**
     *The main method of the program.
     * switch cases to select which method to implement
     * @param args
     */
    public static void main(String[] args){


        WestminsterSkinConsultationManager wmin = new WestminsterSkinConsultationManager();
        boolean start = true;
        do {
            try {
                System.out.println("""
                                    
                           <--------Menu-------->
                            
                          1. Add a Doctor
                          2. Delete a Doctor
                          3. View all the Doctors
                          4. Save data to a file
                          5. Load data from file
                          6. Load the GUI
                          7. Exit from the Program
                          
                          ** Enter your Choice... \s""");

                String input = scanner.next();
                switch (input) {
                    case "1" -> wmin.addDoctor();
                    case "2" -> wmin.deleteDoctor();
                    case "3" -> wmin.printsDoctors();
                    case "4" -> wmin.saveFile();
                    case "10" -> wmin.readFile();
                    case "5" -> wmin.loadData();
                    case "6" -> new GUImain();
                    case "7" -> start = false;
                    default -> System.out.println("\nInvalid input please try again later\n");
                }
            }catch (Exception e){
                System.out.println("Invalid input");
            }
        }while (start);
    }

    /**
     *displaying all the details method.
     * @return run
     */
    public String allDetails(){
        String run= "stop";
        for (Doctor doctor : doctorArray){
            run  = "start";
            System.out.println("--------------------------------------");
            System.out.println("Name           : " + doctor.getName());
            System.out.println("Surname        : " + doctor.getSurname());
            System.out.println("Date of Birth  : " + doctor.getDob());
            System.out.println("Mobile number  : " + doctor.getM_number());
            System.out.println("License number : " + doctor.getLicensenum());
            System.out.println("Specialisation : " + doctor.getSpec());
        }
        return run;
    }

    /**
     *Adding a doctor and validating the inputs method.
     */
    @Override
    public void addDoctor(){
        boolean start = true;
        String vali = "^[A-Za-z]\\w{5,29}$";
        Pattern pattern = Pattern.compile(vali);
        if (10>doctorArray.size()){
            System.out.println("------------Add a Doctor--------------");
            System.out.println("Enter your Name :-->");
            String name = scanner.next();
            System.out.println("Enter your SurName :-->");
            String sname = scanner.next();
            Matcher first = pattern.matcher(name);
            Matcher second = pattern.matcher(sname);
            if (first.matches() && second.matches()){
                System.out.println("Enter the Mobile Number :-->");
                String mNumber = scanner.next();
                System.out.println("Enter the date of Birth :-->");
                LocalDate dob = LocalDate.parse(scanner.next());
                if (mNumber.length() == 10) {
                    Integer.parseInt(mNumber);
                    System.out.println("Enter the License Number :-->");
                    String lNumber = scanner.next();
                    for (Doctor doctor : doctorArray) {
                        if (lNumber.equals(doctor.getLicensenum())) {
                            System.out.println("\n\nMedical License number already exists");
                            System.out.println("Please re enter the correct inputs");
                            start = false;
                            break;
                        }
                    }
                    if (start) {
                        System.out.println("Enter the Specialisation :-->");
                        String spec = scanner.next();
                        doctorArray.add(new Doctor(name, sname, dob, mNumber, lNumber, spec));
                        System.out.println("\n...Doctor added successfully...!!");
                    }
                }else {
                    System.out.println("Please Check your Mobile Number !!!");
                }
            }else {
                System.out.println("Please Check the Entered First name or Sure name ");
            }
        }
    }

    /**
     *deleting a doctor from the doctor array list
     */
    @Override
    public void deleteDoctor(){
        System.out.println("""
                            ------------Delete Doctors------------
                           
                            """);
        if (allDetails().equals("start")){
            System.out.println("\n Enter the medical License that you want to remove :");
            String delete = scanner.next();
            for (int x = 0 ; x < doctorArray.size(); x++){
                if (delete.equals(doctorArray.get(x).getLicensenum())){
                    System.out.println("\n----------List of Details of Erasing Doctor----------\n\n");
                    System.out.println("Name            : " + doctorArray.get(x).getName());
                    System.out.println("Surname         : " + doctorArray.get(x).getSurname());
                    System.out.println("Date of Birth   : " + doctorArray.get(x).getDob());
                    System.out.println("Mobile number   : " + doctorArray.get(x).getM_number());
                    System.out.println("License Number  : " + doctorArray.get(x).getLicensenum());
                    System.out.println("Specialisation  : " + doctorArray.get(x).getSpec());


                    doctorArray.remove(x);
                    if (doctorArray.size() == 1){
                        System.out.println("""
                                
                                ------------Successfully removed the doctor's details------------
                                            
                                """);
                        System.out.println("\t\tThere is one doctor remaining on the List");
                        allDetails();
                    } else if (doctorArray.size() > 1) {
                        System.out.println("""
                                
                                ------------Successfully removed the doctor's details------------
                                     
                                """);
                        System.out.println("There are " + doctorArray.size() + " Doctors exists now ");
                        allDetails();
                    }else {
                        System.out.println("\n------------Successfully removed the doctor's details------------\n");
                        System.out.println("\nThere are no any Doctors available at the moment !!!! ");
                    }
                }else {
                    //System.out.println("\nThere is no Doctor with this License Number !!!! ");
                }
            }
        }else {
            System.out.println("Can't Find any Doctor details !!!!");
        }
    }


    /**
     *printing the lust of the doctors.
     */
    @Override
    public void printsDoctors(){
        System.out.println("-----------------------------------------");
        System.out.println("\t\t\tList of Doctors\n-----------------------------------------");
        if (doctorArray.size() == 0){
            System.out.println("\nThere are no Doctors available at the moment !!");
        }else {
            allDetails();
        }
    }

    /**
     *saving the data of the array to a file.
     */
    @Override
    public void saveFile(){
        try {
            BufferedWriter text = new BufferedWriter(new FileWriter("Doctors.txt"));
            for (Doctor doctor : doctorArray){
                text.write(doctor.getName() + "\n" + doctor.getSurname() + "\n" + doctor.getDob() + "\n" + doctor.getM_number() + "\n" + doctor.getLicensenum() + "\n" + doctor.getSpec() + "\n\n");
            }
            System.out.println("Data Stored Successfully in Doctors.txt File !!");
            text.close();
        }catch (IOException e){
            System.out.println("Error in the System !!");
        }
    }

    /**
     *reading the array
     */
    @Override
    public void readFile(){
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader("Doctors.txt"));
            while ((line = reader.readLine()) != null){
                System.out.println(line);
            }
            reader.close();
        }catch (IOException e){
            System.out.println("Error in the System !!");
        }
    }


    /**
     *Loading data into the array so user can see the previous added inputs.
     */
    @Override
    public void loadData() {
        try {
            String tempdetails;
            ArrayList<String> tempArray = new ArrayList<>();
            BufferedReader tempread = new BufferedReader(new FileReader("Doctors.txt"));
            while ((tempdetails = tempread.readLine()) != null) {
                if (tempdetails.equals("")) {
                    continue;
                } else {
                    tempArray.add(tempdetails);
                }
            }
            for (int x = 0; x <= (tempArray.size() / 6); x++) {
                if (doctorArray.size() == 0) {
                    doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                    tempArray.subList(0, 6).clear();
                } else {
                    if (doctorArray.size() <= 10) {
                        boolean read = true;
                        for (int y = 0; y <= (tempArray.size() / 6); y++) {
                            for (Doctor doctor : doctorArray) {
                                if (doctor.getLicensenum().equals(tempArray.get(4))) {
                                    read = false;
                                    tempArray.subList(0, 6).clear();
                                } else {
                                    read = true;
                                }
                            }
                        }
                        if (read) {
                            doctorArray.add(new Doctor(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), tempArray.get(4), tempArray.get(5)));
                            tempArray.subList(0, 6).clear();
                        }
                    } else {
                        break;
                    }
                }
            }
            System.out.println("Loaded data successfully");
            tempread.close();
        } catch (IOException e) {
            System.out.println("No previous data found !!!!");
        }
    }
}