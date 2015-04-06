import module.Checker;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Return extends JFrame {
    private JPanel Panel;
    private JButton returnButton;
    private JTextField dvdIDField;
    private JCheckBox brokenBox;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    private Controller controller;

    private void close() {
        this.dispose();
    }

    public Return() {
        super("Return");
        setContentPane(Panel);
        setLocationRelativeTo(null); // to set position center on start
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(returnButton);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Checker.isNumber(dvdIDText)) {
                    int dvdID = Integer.parseInt(dvdIDText);
                    if (controller.findDVD(dvdID) != null) {
                        String dayText = dayField.getText();
                        if (Checker.isNumber(dayText)) {
                            int day = Integer.parseInt(dayText);
                            String monthText = monthField.getText();
                            if (Checker.isNumber(monthText)) {
                                int month = Integer.parseInt(monthText);
                                String yearText = yearField.getText();
                                if (Checker.isNumber(yearText)) {
                                    int year = Integer.parseInt(yearText);
                                    if (year > 0) {
                                        if ((month > 0) && (month < 13)) {
                                            if ((day > 0) && (day <= Checker.endDayofMonth(month, year))) {
                                                int memberID = controller.findDVD(dvdID).getMemberID();
                                                int balance = controller.returnDVD(dvdID, day, month, year, brokenBox.isSelected());
                                                if (balance >= 0) {
                                                    JOptionPane.showMessageDialog(null, "OK : " + controller.findMember(memberID).getFirstName() + " must pay " + balance + " Baht", "Complete", JOptionPane.PLAIN_MESSAGE);
                                                    close();
                                                } else if (balance == -1) {
                                                    JOptionPane.showMessageDialog(null, "Error : Not rented", "Error / DVD", JOptionPane.ERROR_MESSAGE);
                                                    close();
                                                } else if (balance == -3) {
                                                    JOptionPane.showMessageDialog(null, "Error : Incorrect Date", "Error / Date", JOptionPane.ERROR_MESSAGE);
                                                }
                                            } else {
                                                JOptionPane.showMessageDialog(null, "Error : Day incorrect", "Error / Day Field", JOptionPane.ERROR_MESSAGE);
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(null, "Error : Month incorrect", "Error / Month Field", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Error : Year incorrect", "Error / Year Field", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Error : Please input only number in Year field", "Error / Year Field", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error : Please input only number in Month field", "Error / Month Field", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error : Please input only number in Day field", "Error / Day Field", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Error : Can't found this DVD ID", "Error / DVD ID Field", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error : Please input only number in DVD ID field", "Error / DVD ID Field", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Return(Controller controller) {
        this();
        this.controller = controller;
    }

    public Return(Controller controller, int dvdID) {
        this(controller);
        dvdIDField.setText(dvdID + "");
    }
}
