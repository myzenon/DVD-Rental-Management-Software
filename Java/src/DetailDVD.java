import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import module.Checker;

public class DetailDVD extends JFrame {
    private JPanel Panel;
    private JButton rButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField dvdIDField;
    private JLabel statusLable;
    private JTextField nameField;
    private JTextField categoryField;
    private JTextField typeField;
    private JTextField priceField;

    private Controller controller;
    private boolean editMode;
    private DVD dvd;

    private void close() {
        this.dispose();
    }

    public DetailDVD() {
        super("Search");
        setContentPane(Panel);
        setLocationRelativeTo(null); // to set position center on start
        pack();
        setVisible(true);
        getRootPane().setDefaultButton(searchButton);
        this.editMode = false;
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Checker.isNumber(dvdIDText))
                    setDataDVD(controller.findDVD(Integer.parseInt(dvdIDText)));
                else
                    JOptionPane.showMessageDialog(null, "Error : Please input only number in DVD ID field", "Error / DVD ID Field", JOptionPane.ERROR_MESSAGE);
            }
        });
        rButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (dvd.getMemberID() != -1) {
                    new Return(controller, dvd.getDvdID());
                } else {
                    new Rental(controller, dvd.getDvdID());
                }
                close();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dvdIDText = dvdIDField.getText();
                if (Checker.isNumber(dvdIDText)) {
                    int choice = JOptionPane.showConfirmDialog(null, "Are you sure to delete ?", "Delete Confimation", JOptionPane.YES_NO_OPTION);
                    if (choice == 0) {
                        controller.removeDVD(Integer.parseInt(dvdIDText));
                        close();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Error : Please input only number in DVD ID field", "Error / DVD ID Field", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        editButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!editMode) {
                    nameField.setEnabled(true);
                    categoryField.setEnabled(true);
                    typeField.setEnabled(true);
                    priceField.setEnabled(true);
                    rButton.setEnabled(false);
                    searchButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    editMode = true;
                } else {
                    // Wait for add menu
                    nameField.setEnabled(false);
                    categoryField.setEnabled(false);
                    typeField.setEnabled(false);
                    priceField.setEnabled(false);
                    rButton.setEnabled(true);
                    searchButton.setEnabled(true);
                    deleteButton.setEnabled(true);
                    editMode = false;
                }
            }
        });
    }

    public DetailDVD(Controller controller) {
        this();
        this.controller = controller;
    }

    public DetailDVD(Controller controller, DVD dvd) {
        this(controller);
        setDataDVD(dvd);
    }

    private void setDataDVD (DVD dvd) {
        nameField.setEnabled(false);
        nameField.setDisabledTextColor(new Color(0, 0, 0));
        categoryField.setEnabled(false);
        categoryField.setDisabledTextColor(new Color(0, 0, 0));
        typeField.setEnabled(false);
        typeField.setDisabledTextColor(new Color(0, 0, 0));
        priceField.setEnabled(false);
        priceField.setDisabledTextColor(new Color(0, 0, 0));
        if(dvd != null) {
            dvdIDField.setText(dvd.getDvdID() + "");
            nameField.setText(dvd.getName());
            categoryField.setText(controller.findCategory(dvd.getCategoryID()));
            typeField.setText(dvd.getType() ? "New" : "Old");
            priceField.setText(dvd.getPrice() + "");
            statusLable.setForeground(dvd.getMemberID() == -1 ? new Color(0, 150, 0) : new Color(255, 0, 0));
            statusLable.setText(dvd.getMemberID() == -1 ? "Avaliable" : "Rented by " + controller.findMember(dvd.getMemberID()).getFirstName());
            this.setTitle(dvd.getName());
            if(dvd.getMemberID() == -1) {
                rButton.setText("Rental");
                rButton.setEnabled(true);
            }
            else {
                rButton.setText("Return");
                rButton.setEnabled(true);
            }
            editButton.setEnabled(true);
            deleteButton.setEnabled(true);
        }
        else {
            nameField.setText("Not Found");
            categoryField.setText("Not Found");
            typeField.setText("Not Found");
            priceField.setText("Not Found");
            statusLable.setForeground(new Color(0, 0, 0));
            statusLable.setText("Not Found");
            this.setTitle("Not Found");
            rButton.setEnabled(false);
            editButton.setEnabled(false);
            deleteButton.setEnabled(false);
        }
        this.dvd = dvd;
    }

}
