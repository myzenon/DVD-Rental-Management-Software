import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Program extends JFrame {
    private JPanel Panel;
    private JButton rentalButton;
    private JButton returnButton;
    private JButton DVDButton;
    private JButton categoryButton;
    private JButton memberButton;
    private JButton settingsButton;
    private JLabel totalMemberLabel;
    private JLabel totalDVDLabel;

    private Controller controller;

    private void close() {
        this.dispose();
    }

    public Program() {
        super("DVD Rental Management Software");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        rentalButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Rental(controller);
            }
        });
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Return(controller);
            }
        });
    }


    public Program(Controller controller) {
        this();
        this.controller = controller;
        totalDVDLabel.setText("Total DVD : " + controller.countDVD());
        totalMemberLabel.setText("Total Member : " + controller.countMember());
    }

}
