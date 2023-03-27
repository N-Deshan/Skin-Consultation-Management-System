import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.*;

public class AvailableDoctors extends JFrame{

    /**
     * Creating a 2d array and assigning values in to a table.
     * creating a method to sort the surnames of the doctors to sort them ascending order.
     * @param MainMenu
     * @param doctorArray
     * @param sort
     */
    public AvailableDoctors(GUImain MainMenu,ArrayList<Doctor> doctorArray,boolean sort){

        String[] ColumnHead ={"Name", "Surname", "Date of Birth", "Mobile Number", "License Number", "Specialization"};
        String[][] TwoDArray = new String[doctorArray.size()][ColumnHead.length];


        if (sort){
            for (int x = 0; x<doctorArray.size(); x++){
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
        }else {
            int size = doctorArray.size();
            String [] array = new String[size];
            for (int i = 0; i<size; i++){
                array[i] = doctorArray.get(i).getSurname();
            }
            Arrays.sort(array);
            for (int n = 0; n<array.length; n++){
                for (Doctor doctor : doctorArray) {
                    if (array[n].equals(doctor.getSurname())) {
                        for (int y = 0; y < ColumnHead.length; y++) {
                            if (y == 0) {
                                TwoDArray[n][y] = doctor.getName();
                            } else if (y == 1) {
                                TwoDArray[n][y] = doctor.getSurname();
                            } else if (y == 2) {
                                TwoDArray[n][y] = String.valueOf(doctor.getDob());
                            } else if (y == 3) {
                                TwoDArray[n][y] = doctor.getM_number();
                            } else if (y == 4) {
                                TwoDArray[n][y] = doctor.getLicensenum();
                            } else {
                                TwoDArray[n][y] = doctor.getSpec();
                            }
                        }
                    }
                }
            }
        }

        /**
         * setting the sizes and default operations of the frame.
         */
        setSize(725,450);
        setResizable(false);
        setFocusable(false);
        setTitle("Available Doctors");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());
        this.setBackground(new Color(173, 173, 235));

        JTable table = new JTable();
        TableModel model = new DefaultTableModel(TwoDArray,ColumnHead);
        table.setModel(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(650,300));
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(scrollPane);
        add("North",panel);
        panel.setBackground(new Color(173, 173, 235));

        JButton sortBtn = new JButton("Sort");
        sortBtn.setPreferredSize(new Dimension(100,40));
        sortBtn.addActionListener(e -> {
            new AvailableDoctors(MainMenu,doctorArray,false).setVisible(true);
            dispose();
        });
        add(sortBtn);

        JButton backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(100,40));
        backBtn.addActionListener(e -> {
            MainMenu.visible();
            dispose();
        });
        add(backBtn);
    }
}