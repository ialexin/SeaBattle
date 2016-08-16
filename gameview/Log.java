package gameview;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Илья on 08.05.2016.
 */
public class Log extends JPanel implements ActionListener{

    public JTextArea textArea;
    public JTextField textField;
    public JLabel label;

    public Log() {
        super(new GridBagLayout());

        label = new JLabel("Message: ");

        textField = new JTextField(20);
        textField.addActionListener(this);



        textArea = new JTextArea(10, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;


        c.fill = GridBagConstraints.HORIZONTAL;
        add(label, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);



        scrollPane.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createCompoundBorder(BorderFactory
                        .createTitledBorder("Chat dlya Igora"), BorderFactory
                        .createEmptyBorder(5, 5, 5, 5)), scrollPane
                        .getBorder()));

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
        setSize(300, 400);
    }

    public void actionPerformed(ActionEvent event){
        String text = textField.getText();
        textArea.append("Pinckman:  " + text + "\n");
        textField.setText("");
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
}
