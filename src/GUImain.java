import javax.swing.*;
import java.awt.*;

/**
 * Creating the variables
 */
public class GUImain extends JFrame{
    JFrame Mainframe;
    JButton viewAll;
    JButton addCons;
    JButton exit;
    JLabel head;
    JButton past;


    /**
     * creating the main gui of the program.
     * adding labels and other stuff
     * Adding action listeners.
     */
    public GUImain() {

        Mainframe = new JFrame("Skin Consultation Manager");
        Mainframe.setLayout(null);
        Mainframe.getContentPane().setBackground(new Color(173, 173, 235));
        ImageIcon imageIcon = new ImageIcon("example2.jpeg");
        Mainframe.setIconImage(imageIcon.getImage());
        Mainframe.setResizable(false);

        Color mainBtns = new Color(204, 204, 255);
        Font mainButtons = new Font("SansSerif", Font.BOLD, 16);

        head=  new JLabel("Welcome to Skin Consultation Centre !!!");
        head.setBounds(180, -20, 500,200);
        Font headFont = new Font("SansSerif", Font.BOLD, 24);
        head.setFont(headFont);
        Mainframe.add(head);

        viewAll = new JButton("View All the available Doctors");
        viewAll.setBounds(100,180,250,50);
        viewAll.setFont(mainButtons);
        viewAll.setBackground(mainBtns);
        Mainframe.add(viewAll);

        addCons = new JButton("Add a new Consultation");
        addCons.setBounds(100,255,250,50);
        addCons.setFont(mainButtons);
        addCons.setBackground(mainBtns);
        Mainframe.add(addCons);

        exit = new JButton("Exit");
        exit.setBounds(100,350,100,40);
        exit.setFont(mainButtons);
        exit.setBackground(mainBtns);
        Mainframe.add(exit);

        past = new JButton("Past Consul");
        past.setBounds(200,350,100,40);
        past.setFont(mainButtons);
        past.setBackground(mainBtns);
        Mainframe.add(past);


        Mainframe.setSize(800,500);
        Mainframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Mainframe.setVisible(true);

        viewAll.addActionListener(e -> {
            Mainframe.setVisible(false);
            new AvailableDoctors(this, WestminsterSkinConsultationManager.doctorArray,true).setVisible(true);
        });

        addCons.addActionListener(e -> {
            Mainframe.setVisible(false);
            new AddConsultation(this).setVisible(true);
        });

        exit.addActionListener(e -> {
            Mainframe.dispose();
        });

        past.addActionListener(e -> {
            Mainframe.setVisible(false);
            new ViewPastConsultation(this).setVisible(true);
        });

    }

    /**
     * Creating a method to make the frame visible
     */
    public void visible(){
        Mainframe.setVisible(true);
    }
}