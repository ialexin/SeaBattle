package gameview;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Илья on 21.04.2016.
 */
public class AboutDialog extends JDialog {
    public AboutDialog(JFrame owner){
        JLabel label = new JLabel("<html><h1><i>Sea Battle</i></h1><hr>By Alexin Ilya and Nobody else</html>");
        add(label);

        JButton ok = new JButton("OK");
        ok.addActionListener(event -> {
            setVisible(false);
        });

        JPanel panel =  new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        setSize(600, 400);
        setLocationRelativeTo(getOwner());
    }
}
