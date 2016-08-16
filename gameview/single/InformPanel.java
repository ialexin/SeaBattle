package gameview.single;

import logic.Data;
import logic.FieldModel;
import logic.enums.ShipType;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Илья on 03.07.2016.
 */
public class InformPanel extends JPanel {
    public JRadioButton oneDeck;
    public JRadioButton twoDeck;
    public JRadioButton threeDeck;
    public JRadioButton fourDeck;

    public InformPanel(FieldModel model) {
        super();
        oneDeck = new JRadioButton("One Deck ships: " + model.getFleet().get(ShipType.OneDeck));
        twoDeck = new JRadioButton("Two Deck ships: " + model.getFleet().get(ShipType.TwoDeck));
        threeDeck = new JRadioButton("Three Deck ships: " + model.getFleet().get(ShipType.ThreeDeck));
        fourDeck = new JRadioButton("Four Deck ships: " + model.getFleet().get(ShipType.FourDeck));

        setLayout(new GridLayout(4, 1));
        setBackground(Color.WHITE);

        addRadioButtonListeners();

        ButtonGroup group = new ButtonGroup();
        group.add(oneDeck);
        group.add(twoDeck);
        group.add(threeDeck);
        group.add(fourDeck);

        setSize(170, 150);
        add(oneDeck);
        add(twoDeck);
        add(threeDeck);
        add(fourDeck);
        setVisible(true);
    }

    private void addRadioButtonListeners(){
        oneDeck.setBackground(Color.WHITE);
        twoDeck.setBackground(Color.WHITE);
        threeDeck.setBackground(Color.WHITE);
        fourDeck.setBackground(Color.WHITE);

        oneDeck.addItemListener(event -> {
            Data.currentType = ShipType.OneDeck;
            System.out.println("One deck has chosen");
        });

        twoDeck.addItemListener(event -> {
            Data.currentType = ShipType.TwoDeck;
        });
    }
}
