import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 *initializing variables
 */
public class ViewPastConsultation extends JFrame {
    int id;

    /**
     *creating a frame and adding labels and text fields.
     * @param Mainmenu
     */
    ViewPastConsultation(GUImain Mainmenu){

        this.setSize(850,700);
        this.setTitle("Details of the past consultation");
        this.setResizable(false);

        this.setLayout(null);
        JButton OK = new JButton("OK");

        ArrayList<Consultation> consultationArray = WestminsterSkinConsultationManager.consultationArray;

        JLabel pop = new JLabel("Consultation Info");
        pop.setBounds(370,30,500,20);
        pop.setFont(new Font("SansSerif", Font.BOLD, 16));

        JLabel enterid = new JLabel("Enter the ID :");
        enterid.setBounds(140,600,100,30);
        JTextField enterIdtext = new JTextField();
        enterIdtext.setBounds(250,600,70,30);

        JTextArea textArea = new JTextArea();
        textArea.setBounds(100,70,600,475);


        this.add(pop);
        this.add(enterid);
        this.add(enterIdtext);
        this.add(textArea);

        /**
         *assigning a variable and check through the consultation array and adding to the text area(not working yet).
         */
        OK.setBounds(510,600,70,30);
        OK.addActionListener(e -> {

        id = Integer.parseInt(enterIdtext.getText());
        for (Consultation consultation : consultationArray) {
            if (consultation.getConsulID().equals(id)) {
                System.out.println(consultation.toString());
                textArea.setText(consultation.toString());
            }
        }
        });

        this.add(OK);
        this.setVisible(true);
    }
}
