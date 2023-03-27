import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *Creating the consultation array list
 * creating and assigning variables
 */
public class AddConsultation extends JFrame {

    private final ArrayList<Consultation> consultationArray = WestminsterSkinConsultationManager.consultationArray;

    String name, surname, mobile, patientID,doctorID,note;
    double cost;

    LocalDate dob, consulDate;
    LocalTime  startTime, endTime;
    String filename;
    private int again = 0;
    private int patID;

    private final JButton enterphotobtn;
    private JComboBox enterStartTCbH,enterStartTCbMin,enterEndTCbH,enterEndTCbMin,enterConsulDocCb;
    private final JTextArea enternote;
    private JLabel enterNamelbl,enterSurNamelbl,enterDoblbl,enterMobilelbl,enternotelbl,enterStartTlbl,enterEndTlbl,enterFormatlbl,enterPatientIDlbl,enterphotolbl,enterConsulDatelbl,enterConsulDateFlbl,enterConsulDoclbl;
    private JTextField enterNameText,enterSurNameText,enterDobText,enterMobileText,enterPatientIDText,enterConsulDateText;

    /**
     *Loading data auto.
     * creating the doctor arraylist and passing the values
     * creating a 2d array and passing the values of the arrays in to a table
     * @param MainMenu
     */
    public  AddConsultation(GUImain MainMenu){
        loadData();

        String[] ColumnHead ={"Name", "Surname", "Date of Birth", "Mobile Number", "License Number", "Specialisation"};
        ArrayList<Doctor> doctorArray = WestminsterSkinConsultationManager.doctorArray;
        String[][] TwoDArray = new String[doctorArray.size()][ColumnHead.length];

        for (int x = 0; x< doctorArray.size(); x++){
            for (int y = 0; y<ColumnHead.length; y++){
                if (y == 0){
                    TwoDArray[x][y] = doctorArray.get(x).getName();
                } else if (y == 1) {
                    TwoDArray[x][y] = doctorArray.get(x).getSurname();
                }else if (y == 2) {
                    TwoDArray[x][y] = String.valueOf(doctorArray.get(x).getDob());
                }else if (y == 3) {
                    TwoDArray[x][y] = doctorArray.get(x).getM_number();
                }else if (y == 4) {
                    TwoDArray[x][y] = doctorArray.get(x).getLicensenum();
                }else{
                    TwoDArray[x][y] = doctorArray.get(x).getSpec();
                }
            }
        }

        String[] consulIDA =new String[doctorArray.size()];

        for (int z = 0; z< doctorArray.size(); z++){
                consulIDA [z] = doctorArray.get(z).getLicensenum();
        }

        setSize(850,800);
        setResizable(false);
        setTitle("Add a new Consultation");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JTable table = new JTable();
        TableModel model = new DefaultTableModel(TwoDArray,ColumnHead);

        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650,200));
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(scrollPane);
        add("North",panel);
        panel.setBackground(new Color(173, 173, 235));

        /**
         * creating a consultation panel and adding the labels and assigning the text fields.
         * Adding the buttons
         */
        JPanel consulPanel= new JPanel();
        consulPanel.setPreferredSize(new Dimension(700,200));
        consulPanel.setLayout(null);
        consulPanel.setBackground(new Color(173, 173, 235));

        enterNamelbl = new JLabel("1. Name:");
        enterNamelbl.setBounds(70,100,100,20);
        enterNameText = new JTextField(1);
        enterNameText.setBounds(170,100,100,25);

        enterSurNamelbl = new JLabel("2. SurName:");
        enterSurNamelbl.setBounds(70,150,100,20);
        enterSurNameText = new JTextField(1);
        enterSurNameText.setBounds(170,150,100,25);

        enterDoblbl = new JLabel("3. Date of Birth:");
        enterDoblbl.setBounds(70,200,100,20);
        enterDobText = new JTextField(1);
        enterDobText.setBounds(170,200,100,25);

        enterFormatlbl = new JLabel("(YYYY-MM-DD)");
        enterFormatlbl.setBounds(75,215,100,20);

        enterMobilelbl = new JLabel("4. Mobile Number:");
        enterMobilelbl.setBounds(300,100,105,20);
        enterMobileText = new JTextField(1);
        enterMobileText.setBounds(420,100,100,25);

        enterStartTlbl = new JLabel("5. Start Time:");
        enterStartTlbl.setBounds(300,150,120,20);
        enterStartTCbH = new JComboBox(new String[] {"HH","07","08","09","10","11","12","13","14","15","16","17"});
        enterStartTCbH.setBounds(420,150,60,20);
        enterStartTCbMin = new JComboBox(new String[] {"MIN","00","15","30","45"});
        enterStartTCbMin.setBounds(490,150,60,20);

        enterEndTlbl = new JLabel("6. End Time:");
        enterEndTlbl.setBounds(300,200,120,20);
        enterEndTCbH = new JComboBox(new String[] {"HH","07","08","09","10","11","12","13","14","15","16","17"});
        enterEndTCbH.setBounds(420,200,60,20);
        enterEndTCbMin = new JComboBox(new String[] {"MIN","00","15","30","45"});
        enterEndTCbMin.setBounds(490,200,60,20);

        enterConsulDoclbl = new JLabel("10. Select Doctor :");
        enterConsulDoclbl.setBounds(570,200,120,20);
        enterConsulDocCb = new JComboBox(consulIDA);
        enterConsulDocCb.setBounds(690,200,100,20);

        enterphotobtn = new JButton("Photo");
        enterphotobtn.setBounds(690,370,70,30);
        enterphotolbl = new JLabel();
        enterphotolbl.setBounds(450,300,200,170);
        enterphotolbl.setBackground(Color.WHITE);
        enterphotolbl.setOpaque(true);

        enternotelbl = new JLabel("7. Enter a note here :");
        enternotelbl.setBounds(50,270,120,20);
        enternote = new JTextArea();
        enternote.setBounds(50,300,350,200);

        enterPatientIDlbl = new JLabel("8. Patient ID :");
        enterPatientIDlbl.setBounds(300,250,120,20);
        enterPatientIDText = new JTextField(1);
        enterPatientIDText.setBounds(420,250,120,25);

        enterConsulDatelbl = new JLabel("9. Consultation Date :");
        enterConsulDatelbl.setBounds(560,100,120,20);
        enterConsulDateFlbl= new JLabel("(YYYY-MM-DD)");
        enterConsulDateFlbl.setBounds(570,115,120,20);
        enterConsulDateText = new JTextField(1);
        enterConsulDateText.setBounds(690,100,120,25);

        JButton addbtn = new JButton("Add");
        addbtn.setBounds(550,500,100,40);


        /**
         *Validating names and other inputs using Joption error pane if error occured it will pop up an error message
         */
        addbtn.addActionListener(e -> {
            try {
                String vali = "^[A-Za-z]\\w{2,29}$";
                Pattern pattern = Pattern.compile(vali);
                name = enterNameText.getText();
                surname = enterSurNameText.getText();
                Matcher n = pattern.matcher(name);
                Matcher s = pattern.matcher(surname);
                if (n.matches() && s.matches()){
                    try {
                        dob = LocalDate.parse(enterDobText.getText());
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null,"Check the entered Birthday","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Check the entered First and Surnames","Error",JOptionPane.ERROR_MESSAGE);
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"System Error!","Error",JOptionPane.ERROR_MESSAGE);
            }

            try{
                mobile = enterMobileText.getText();
                if (mobile.length() == 10) {
                    System.out.println("1");
                    try {
                        Integer.parseInt(mobile);
                        System.out.println("2");
                        try {
                            System.out.println("3");
                            patientID = String.valueOf(Integer.parseInt(enterPatientIDText.getText()));
                            doctorID = (String) enterConsulDocCb.getSelectedItem();
                            try {
                                System.out.println("4");
                                String stimeHours = (String) enterStartTCbH.getSelectedItem();
                                String stimeMin = (String) enterStartTCbMin.getSelectedItem();
                                startTime = LocalTime.parse(stimeHours + ":" + stimeMin);
                                System.out.println(startTime);

                                String etimeHours = (String) enterEndTCbH.getSelectedItem();
                                String etimeMin = (String) enterEndTCbMin.getSelectedItem();
                                endTime = LocalTime.parse(etimeHours + ":" + etimeMin);
                                System.out.println(endTime);

                            }catch (Exception ignored){
                                JOptionPane.showMessageDialog(null,"Check the start time and end time","Error",JOptionPane.ERROR_MESSAGE);
                            }
                        }catch (Exception ignored){
                            JOptionPane.showMessageDialog(null,"Check the Patient ID","Error",JOptionPane.ERROR_MESSAGE);
                        }
                    }catch (Exception ignored){
                        JOptionPane.showMessageDialog(null,"Invalid Mobile number","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }catch (Exception ignored){
                JOptionPane.showMessageDialog(null,"Check the entered mobile number","Error",JOptionPane.ERROR_MESSAGE);
            }
            try {
                consulDate = LocalDate.parse(enterConsulDateText.getText());
                if (consulDate.isAfter(LocalDate.now()) && consulDate.isBefore(LocalDate.now().plusYears(3))){
                    System.out.println(consultationArray.get(0));
                }
            }catch (Exception ignored){
                //JOptionPane.showMessageDialog(null,"Check the entered date","Error",JOptionPane.ERROR_MESSAGE);
            }

            /**
             *Adding a photo to a button
             * Validating the inputs and if there are same consultaion time and date to a single doctor ...another doctor will randomly assigned.
             */
            enterphotobtn.addActionListener(e1 -> {
              try {
                   JFileChooser chooser = new JFileChooser();
                   chooser.showOpenDialog(null);
                   File photo = chooser.getSelectedFile();
                   enterphotolbl.setIcon(new ImageIcon(photo.toString()));
                   }catch (Exception ignored){
                  existcheck();
              }
              });
            existcheck();
            if (name != null && surname != null && dob != null && mobile != null && patientID != null && startTime != null && endTime != null && consulDate != null && doctorID != null){
                consultationArray.add(new Consultation(name, surname, dob, mobile, patientID, startTime, endTime, consulDate,doctorID,cost,""));
                saveData();
                System.out.println(consultationArray.get(consultationArray.size()-1));
            }
            this.dispose();
            new AddConsultationMessage();

        });

        /**
         *creating a back button and adding the action listener.
         */

        JButton backbtn = new JButton("Back");
        backbtn.setBounds(670,500,100,40);
        backbtn.addActionListener(e -> {
            this.setVisible(false);
            MainMenu.visible();
        });

        /**
         *Adding the photo to the label.
         */
        enterphotobtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser file=new JFileChooser();
                file.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter =new FileNameExtensionFilter("*.Images",".jpg",".gif","png");
                file.addChoosableFileFilter(filter);
                int result=file.showSaveDialog(null);
                if(result==JFileChooser.APPROVE_OPTION){
                    File selectFile=file.getSelectedFile();
                    String path=selectFile.getAbsolutePath();
                    enterphotolbl.setIcon(ResizeImage(path));
                } else if (result == JFileChooser.CANCEL_OPTION) {
                    System.out.println("No file selected");
                }};

            /**
             *Method to resizing a image and returning an image and adding the labels, text fields to the Java frame.
             */
            public ImageIcon ResizeImage(String ImagePath){
                ImageIcon MyImage=new ImageIcon(ImagePath);
                Image img=MyImage.getImage();
                Image newImg=img.getScaledInstance(enterphotolbl.getWidth(),enterphotolbl.getHeight(),Image.SCALE_SMOOTH);
                ImageIcon image =new ImageIcon(newImg);
                return image;
            }
        });

        consulPanel.add(addbtn);             consulPanel.add(enterConsulDoclbl);     consulPanel.add(backbtn);           consulPanel.add(enterConsulDocCb);
        consulPanel.add(enterNamelbl);       consulPanel.add(enterNameText);         consulPanel.add(enterSurNamelbl);   consulPanel.add(enterSurNameText);
        consulPanel.add(enterDoblbl);        consulPanel.add(enterDobText);          consulPanel.add(enterMobilelbl);    consulPanel.add(enterMobileText);
        consulPanel.add(enternotelbl);       consulPanel.add(enternote);             consulPanel.add(enterStartTlbl);    consulPanel.add(enterEndTlbl);
        consulPanel.add(enterStartTCbH);     consulPanel.add(enterEndTCbH);          consulPanel.add(enterStartTCbH);    consulPanel.add(enterStartTCbMin);
        consulPanel.add(enterEndTCbH);       consulPanel.add(enterEndTCbMin);        consulPanel.add(enterFormatlbl);    consulPanel.add(enterConsulDateFlbl);
        consulPanel.add(enterPatientIDlbl);  consulPanel.add(enterPatientIDText);    consulPanel.add(enterphotolbl);     consulPanel.add(enterphotobtn);
        consulPanel.add(enterConsulDatelbl); consulPanel.add(enterConsulDateText);
        consulPanel.setLayout(null);
        this.add("Center",consulPanel);
        this.setVisible(true);
    }

    /**
     *taking the start time and end time then calculating the cost by multiplying them with the relevant value
     * Then checking the time entered at the moment with the past added times and if there are same id, time , and date it will assign another doctor randomly.
     */
    private void existcheck(){
        Duration duration = Duration.between(startTime,endTime);
        boolean check = true;
        for (Consultation consultation: consultationArray){
            if (consultation.getPatientid().equals(patientID)){
                again++;
            }
        }
        for (int i = 0; i<consultationArray.size(); i++){
            if (Objects.equals(doctorID, consultationArray.get(i).getConsulID())){
                if (((consultationArray.get(i).getStartTime()).isBefore(startTime) &&
                        (consultationArray.get(i).getEndTime()).isAfter(endTime)) ||
                        ((consultationArray.get(i).getStartTime()).isBefore(endTime) &&
                        (consultationArray.get(i).getEndTime()).isAfter(endTime)) ||
                        (consultationArray.get(i).getStartTime()).equals(startTime) ||
                        (consultationArray.get(i).getEndTime()).equals(endTime)){
                    check = false;
                    break;
                }
            }
        }
        if (!check){
            //show msg doctor full
            int dsize = WestminsterSkinConsultationManager.doctorArray.size();
            String[] rand = new String[dsize];
            for (int i = 0; i < dsize; i++){
                rand[i] = WestminsterSkinConsultationManager.doctorArray.get(i).getLicensenum();
            }
            Random random = new Random();
            int randindex = random.nextInt(rand.length);
            doctorID = rand[randindex];
            existcheck();
        }
        if (again == 0){
            cost = (Integer.parseInt(String.valueOf(duration.toHours()))*15);
        }else {
            cost = (Integer.parseInt(String.valueOf(duration.toHours()))*25);
        }
    }

    /**
     *saving datails to a text file.
     */
    private void saveData() {
        try {
            BufferedWriter text = new BufferedWriter(new FileWriter("Consultation.txt",true));
            for (Consultation consultation : WestminsterSkinConsultationManager.consultationArray) {
                text.write(consultation.getName() + "\n" + consultation.getSurname() + "\n" + consultation.getDob() + "\n" + consultation.getM_number() +"\n" + consultation.getPatientid() + "\n" + consultation.getDate() + "\n" + consultation.getStartTime() + "\n" + consultation.getEndTime() + "\n" + consultation.getNotes()+"\n"+consultation.getCost()+"\n\n");
            }
            System.out.println("\nData Stored Successfully in Consultation.txt File !!\n");
            text.close();
        } catch (IOException e) {
            System.out.println("Error in the System !!");
        }
    }

    /**
     *loading data to the relevant array to continue where it ended.
     */
    public void loadData(){
        ArrayList <String> tempArray = new ArrayList<>();
        try {
            String temp;
            BufferedReader readFile =new BufferedReader(new FileReader("Consultation.txt"));
            while (((temp= readFile.readLine()) != null)){
                if(temp.equals("")){
                    continue;
                }else {
                    tempArray.add(temp);
                }
            }
            while ( 0 < (tempArray.size() / 11)) {
                consultationArray.add(new Consultation(tempArray.get(0), tempArray.get(1), LocalDate.parse(tempArray.get(2)), tempArray.get(3), (tempArray.get(4)), LocalTime.parse(tempArray.get(5)), LocalTime.parse(tempArray.get(6)), LocalDate.parse(tempArray.get(7)), (tempArray.get(8)),Double.parseDouble(tempArray.get(9)),tempArray.get(10)));
                tempArray.subList(0, 11).clear();
            }
        }catch (Exception ignored){
        }
    }
}