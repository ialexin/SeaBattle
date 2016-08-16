package gameview.multi;

import network.serverClasses.User;
import network.subjectStates.ConnectState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Илья on 08.05.2016.
 */
public class ConnectDialog extends JPanel {
    private JTextField username;
    private JTextField ip;
    private JTextField port;
    private JButton okButton;
    private JDialog dialog;
    private boolean ok;

    public ConnectDialog() {
        setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
        panel.add(new JLabel("user name: "));
        panel.add(username = new JTextField(""));
        panel.add(new JLabel("ip: "));
        panel.add(ip = new JTextField("127.0.0.1"));
        panel.add(new JLabel("port: "));
        panel.add(port = new JTextField("8080"));

        add(panel, BorderLayout.CENTER);

        okButton = new JButton("OK");

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(event -> {
            dialog.setVisible(false);
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public boolean showDialog(Component parent, String title) {
        ok = false;

        Frame owner = null;
        if (parent instanceof Frame)
            owner = (Frame) parent;
        else owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);

        if (dialog == null || dialog.getOwner() != owner) {
            dialog = new JDialog();
            dialog.add(this);
            dialog.getRootPane().setDefaultButton(okButton);
            dialog.pack();
        }
        dialog.setTitle(title);
        dialog.setVisible(true);
        return ok;
    }

    public String getIp() {
        return ip.getText();
    }

    public String getUsername(){
        return username.getText();
    }

    public String getPort(){
        return port.getText();
    }

    public JButton getOKButton(){
        return okButton;
    }

    public JDialog getDialog(){
        return dialog;
    }

    public JTextField[] getTextFields(){
        return new JTextField[] {username, ip, port};
    }
}
