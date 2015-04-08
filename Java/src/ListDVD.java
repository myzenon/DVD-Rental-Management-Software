import jdk.internal.org.objectweb.asm.tree.analysis.Value;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

public class ListDVD extends JFrame{
    private JPanel Panel;
    private JComboBox categoryComboBox;
    private JList listDVD;

    private Controller controller;

    private void close() {
        this.dispose();
    }

    public ListDVD() {
        super("List of DVD : By Category");
        setContentPane(Panel);
        setLocationRelativeTo(null); // to set position center on start
        pack();
        setVisible(true);
        listDVD.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    new DetailDVD(controller,(DVD)listDVD.getSelectedValue());
                }
            }
        });
        categoryComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

    public ListDVD(Controller controller) {
        this();
        this.controller = controller;
        setListDVD(-1);
        setListCategory();
    }

    private void setListCategory() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) categoryComboBox.getModel();
        model.removeAllElements();

        for(Map.Entry<Integer,String> entry : controller.getCategory().entrySet()) {
            model.addElement(entry);
        }
        categoryComboBox.setModel(model);
    }

    private void setListDVD(int categoryID) {
        DefaultListModel model = new DefaultListModel();
        for(DVD dvd : controller.getListDVD(categoryID)) {
            model.addElement(dvd);
        }
        listDVD.setModel(model);
    }


}
