import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import module.Checker;

public class Rental extends JFrame {
    private JButton rentButton;
    private JPanel Panel;
    private JTextField dvdIDField;
    private JTextField memberIDField;
    private JTextField dayField;
    private JTextField monthField;
    private JTextField yearField;

    private Controller controller;

    public Rental() {
        super("Rental");
        setContentPane(Panel);
        setLocationRelativeTo(null); // to set position center on start
        pack();
        setVisible(true);
        rentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Checker.isNumber(dvdIDText)) {
                    int dvdID = Integer.parseInt(dvdIDText);
                    if (controller.findDVD(dvdID) != null) {
                        String memberIDText = memberIDField.getText();
                        if (Checker.isNumber(memberIDText)) {
                            int memberID = Integer.parseInt(memberIDText);
                            if (controller.findMember(memberID) != null) {
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
                                                        int balance = controller.rentalDVD(dvdID, memberID, day, month, year);
                                                        if (balance > 0) {
                                                            // show balance
                                                        } else {
                                                            // already rented
                                                        }
                                                    } else {
                                                        // day incorrect
                                                    }
                                                } else {
                                                    // month not correct
                                                }
                                            } else {
                                                // year cant minus
                                            }
                                        } else {
                                            // year string
                                        }
                                    } else {
                                        // month string
                                    }
                                } else {
                                    // day string
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "Error : Can't found this Member ID", "Error / Member ID Field", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Error : Please input only number in Member ID field", "Error / Member ID Field", JOptionPane.ERROR_MESSAGE);
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

    public Rental(Controller controller) {
        this();
        this.controller = controller;
    }
}
