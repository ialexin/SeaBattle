package gameview;

import com.oracle.xmlns.internal.webservices.jaxws_databinding.ExistingAnnotationsType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Илья on 08.05.2016.
 */
public class ChoiceFrame extends JOptionPane{
    public ChoiceFrame(){
        setMessage("Choose game mode");
        String[] options = new String[] {"Single", "Multi"};
        setOptions(options);
        JDialog dialog = createDialog(new JFrame(), "SeaBattle");
        dialog.setVisible(true);
        dialog.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
    }
}
