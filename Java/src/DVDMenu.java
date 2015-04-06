import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DVDMenu extends JFrame {
    private JPanel Panel;
    private JButton addButton;
    private JButton searchButton;
    private JButton listButton;
    private JButton backButton;

    private Controller controller;

    private void close() {
        this.dispose();
    }

    public DVDMenu() {
        super("DVD");
        setContentPane(Panel);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(0);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Program(controller);
                close();
            }
        });
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new DetailDVD(controller);
            }
        });
    }

    public DVDMenu(Controller controller) {
        this();
        this.controller = controller;
    }
}
