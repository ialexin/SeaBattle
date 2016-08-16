package gameview.multi;

import gameview.Log;
import network.dataTypes.Message;
import observer.multi.MultiSubject;

import java.awt.event.ActionEvent;
import java.io.IOException;

/**
 * Created by Илья on 09.05.2016.
 */
public class LogMulti extends Log {
    MultiSubject subject;

    public LogMulti(MultiSubject subject){
        super();
        this.subject = subject;
    }
    @Override
    public void actionPerformed(ActionEvent event){
        try {
            Object text = textField.getText();
            textArea.append(subject.getUser().getName() + ":  " + text + "\n");
            textField.setText("");
            textArea.setCaretPosition(textArea.getDocument().getLength());
            subject.out.writeObject(text);
            subject.out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
