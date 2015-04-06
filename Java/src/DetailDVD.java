import javax.swing.*;

public class DetailDVD extends JFrame {
    private JPanel Panel;
    private JButton RButtonButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton searchButton;
    private JTextField dvdIDField;
    private JTextField categoryField;
    private JTextField typeField;
    private JTextField priceField;
    private JLabel statusLable;

    private Controller controller;
    private DVD dvd;

    private void close() {
        this.dispose();
    }

    public DetailDVD() {
        super("DetailDVD");
        setContentPane(Panel);
        setLocationRelativeTo(null); // to set position center on start
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

}
