import javax.swing.*;
import java.util.ArrayList;

public class AddConsultationMessage extends JFrame {
    AddConsultationMessage(){

        this.setSize(500,500);
        this.setTitle("Details of the consultation");
        this.setResizable(false);
        this.setVisible(true);
        this.setLayout(null);
        JButton OK = new JButton("OK");


        ArrayList<Consultation> consultationArray = WestminsterSkinConsultationManager.consultationArray;

        JLabel pop = new JLabel("Consultation Info");
        pop.setBounds(170,30,500,20);

        int pop1 = (consultationArray.size()-1);

        JLabel info = new JLabel();
        info.setText("<html><br><br>"+
                "01.Name                 : "+consultationArray.get(pop1).getName()+"<br>"+
                "02.Surname              : "+consultationArray.get(pop1).getSurname()+"<br>"+
                "03.Date-of-Birth        : "+consultationArray.get(pop1).getDob()+"<br>"+
                "04.Mobile-No            : "+consultationArray.get(pop1).getM_number()+"<br>"+
                "05.Patient-ID           : "+consultationArray.get(pop1).getPatientid()+"<br>"+
                "06.Consultation-Date    : "+consultationArray.get(pop1).getDate()+"<br>"+
                "07.Start-Time           : "+consultationArray.get(pop1).getStartTime()+"<br>"+
                "08.End-Time             : "+consultationArray.get(pop1).getEndTime()+"<br>"+
                "09.Consulted-Doctor     : "+consultationArray.get(pop1).getConsulID()+"<br>"+
                "10.Cost-for-Consultation: "+consultationArray.get(pop1).getCost()+"<br>"+
                "11.Additional-Note      : "+"<br>"+consultationArray.get(pop1).getNotes()+"</html>");

        info.setBounds(40,15,400,400);

        this.add(pop);
        this.add(info);

        OK.setBounds(210,400,70,30);
        OK.addActionListener(e -> {
            this.dispose();
            new GUImain();
        });
        this.add(OK);
    }
}
