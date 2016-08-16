package gameview.single;

import gameview.AboutDialog;
import gameview.Log;
import logic.Data;
import logic.FieldModel;
import logic.GameModel.InitState;
import logic.GameModel.PlayerTurnState;
import logic.GameModel.PreInitState;
import logic.enums.CellState;
import logic.enums.ShipType;
import observer.single.SingleSubject;
import res.SZ;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Илья on 20.04.2016.
 */
public class SingleView extends JFrame{
    protected static Map<CellState, Color> colors;
    private SingleSubject subject;
    public static FieldModel player;
    public static FieldModel enemy;
    protected JMenuBar menuBar;
    public AboutDialog aboutDialog;
    public JMenuItem aboutItem;
    public JPanel buttonPanel;
    public JPanel informPanel;
    public JPanel fieldEN;
    public JPanel fieldPL;
    public JButton starter;
    //public JLabel ships;

    public JLabel currAmount;
    public JButton oneDeck;
    public JLabel twoDeck;
    public JLabel threeDeck;
    public JLabel fourDeck;
    public Log log;

    //MAIN FRAME. Define all GUI here
    public SingleView(FieldModel player, FieldModel enemy) {
        //FIXME убрать отсюда FieldModel или оставить? (интерфейс отдельно от логики?)

        this.player = player;
        this.enemy = enemy;
        this.subject = new SingleSubject();

        colors = new HashMap<>();
        setHashMap();
        getContentPane().setLayout(null);

        currAmount = new shipLabel("curOneDeckNum: " + Data.curShipsAmount);
        oneDeck = new JButton("One Deck ships: " + player.getFleet().get(ShipType.OneDeck));

        /*curDoubleDeckNum;
        curThreeDeckNum;
        curFourDeckNum;

        curShipsAmount;
        */

        starter = new StartButton();
        buttonPanel = new JPanel();
        informPanel = new InformPanel(player);
        fieldEN = new EnemyField("Enemy");
        fieldPL = new PlayerField("Player");


        log = new Log();
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> {
            if(aboutDialog == null)
                aboutDialog = new AboutDialog(this);
            else
                aboutDialog.setVisible(true);
        });
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.add(aboutItem);
        menuBar.add(aboutMenu);

        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setSize(starter.getWidth() * 2, starter.getHeight());

        buttonPanel.setLocation(830, 600);
        fieldPL.setLocation(0, 450);
        //currAmount.setLocation(700, 80);
        informPanel.setLocation(430, 80);
        log.setLocation(800, 200);

        buttonPanel.add(starter);

        getContentPane().add(fieldEN);
        getContentPane().add(fieldPL);
        getContentPane().add(buttonPanel);
        getContentPane().add(currAmount);
        getContentPane().add(log);
        getContentPane().add(informPanel);

        setVisible(true);
        setSize(1200, 960);
        getContentPane().setBackground(Color.WHITE);
        setTitle("BattleShip2.0");
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    protected static void setHashMap() {
        colors.put(CellState.NON_STATED, Color.LIGHT_GRAY);
        colors.put(CellState.WATER, Color.BLUE);
        colors.put(CellState.DECK, Color.GRAY);
        colors.put(CellState.KILLED, Color.RED);
        colors.put(CellState.MISSED, Color.PINK);
    }

    public SingleSubject getSubject(){
        return subject;
    }

    public void setSubject(SingleSubject subject){
        this.subject = subject;
    }

    public void repaint(){
        super.repaint();
        paintComponents(getGraphics());
        fieldPL.paintComponents(getGraphics());
        fieldEN.paintComponents(getGraphics());
        //currAmount.setText(currAmount.getText().substring(0, currAmount.getText().length() - 1) + player.getCurShipsAmount());
    }

    public void customButtonPanel(JButton... buttons){
        buttonPanel.removeAll();
        buttonPanel.setLayout(new FlowLayout());
        for(JButton button : buttons) {
            buttonPanel.add(button);
        }
        this.repaint();
    }

    public JTextField setTextField(String nick) {
        JTextField field = new JTextField(nick);
        field.setEnabled(false);
        field.setBounds(225, 10, 50, 20);
        return field;
    }

    public FieldModel getPlayerField(){
        return player;
    }

    public int convertCol(int x){
        int col = x - (SZ.CELL_SIZE + SZ.REC);
        if(col >= 0)
            col = col / SZ.CELL_SIZE + 1;
        return col;
    }

    public int convertRow(int y){
        int row = y - SZ.CELL_SIZE;
        if(row >= 0)
            row = row / SZ.CELL_SIZE + 1;
        return row;
    }

    ///////////*******
    /////***INNER CLASSES*******///////
    public class PlayerField extends JPanel {

        PlayerField(String nick) {
            setLayout(null);
            add(setTextField(nick));
            //setBackground(Color.CYAN);
            setSize(SZ.FIELD_WIDTH, SZ.FIELD_HEIGHT);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            /*addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int col = convertCol(e.getX());
                    int row = convertRow(e.getY());
                    System.out.println(e.getX());
                    System.out.println("The cell is " + col + ", " + row);
                    player.getCell(row, col).setState(CellState.DECK);
                    repaint();
                }
            });
            */
            //paintComponent(getGraphics());
        }

        @Override
        public void repaint(){
            super.repaint();
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            paintPlayerField(g);
        }

        public void paintPlayerField(Graphics g) {
            for (int row = 1; row < SZ.ROWS; row++) {
                for (int col = 1; col < SZ.COLS; col++) {
                    g.setColor(colors.get(player.getCell(row, col).getState()));
                    g.fillRect(SZ.REC + SZ.CELL_SIZE * col, SZ.CELL_SIZE * row, SZ.GRID_WIDTH, SZ.GRID_WIDTH);
                }
            }
        }


    }

    public class EnemyField extends JPanel{

        EnemyField(String nick) {
            setLayout(null);
            add(setTextField(nick));
            setSize(SZ.FIELD_WIDTH, SZ.FIELD_HEIGHT);
            setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            //addMouseListener()
            //paintComponent(getGraphics());
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            paintEnemyField(g);
        }

        public void paintEnemyField(Graphics g) {
            for (int row = 1; row < SZ.ROWS; row++) {
                for (int col = 1; col < SZ.COLS; col++) {
                    if (enemy.getCell(row, col).isChecked()) {
                        g.setColor(colors.get(enemy.getCell(row, col).getState()));
                    } else {
                        g.setColor(Color.BLACK);
                    }
                    g.fillRect(SZ.REC + SZ.CELL_SIZE * col, SZ.CELL_SIZE * row, SZ.GRID_WIDTH, SZ.GRID_WIDTH);
                }
            }
        }
    }

    private class StartButton extends JButton {

        public StartButton() {
            setSize(130, 60);
            setText("StartGame");
            setFont(new Font("Serif", Font.ITALIC, 20));
            setFocusable(false);
            setVisible(true);
            addActionListener(event -> {
                customButtonPanel(new generateButton(), new createShipButton());
                subject.setGameState(new PreInitState());
            });
        }
    }

    public class generateButton extends JButton{
        public generateButton(){
            setSize(130, 60);
            setText("Generate");
            setFont(new Font("Serif", Font.ITALIC, 20));
            setVisible(true);
            addActionListener(event -> {
                subject.setGameState(new InitState());
                for(Component component : buttonPanel.getComponents())
                    component.setEnabled(false);
                currAmount.setText(currAmount.getText().substring(0, currAmount.getText().length() - 1) + player.getCurShipsAmount()); //мб лучше d InitState()?
                subject.setGameState(new PlayerTurnState());
            });
        }
    }

    public class createShipButton extends JButton{
        public createShipButton() {
            setSize(130, 60);
            setText("Manually");
            setFont(new Font("Serif", Font.ITALIC, 20));
            setVisible(true);
            addActionListener( event -> {
                //subject.setState(0);
                subject.setGameState(new InitState());
                for(Component component : buttonPanel.getComponents()){
                    component.setEnabled(false);
                }
                currAmount.setText(currAmount.getText().substring(0, currAmount.getText().length() - 1) + player.getCurShipsAmount());
            });
        }
    }

    public class shipLabel extends JLabel{
        shipLabel(String label){
            super(label);
            setSize(150, 70);
            setVisible(true);
        }
    }

    public static void main(String[] args){

        FieldModel player = new FieldModel();
        FieldModel enemy = new FieldModel();
        SingleView fr = new SingleView(player, enemy);// Let the constructor do the job

        Scanner in = new Scanner(System.in);
        System.out.println("type smth");
        int row = in.nextInt();
        int col = in.nextInt();
        fr.getPlayerField().getCell(row, col).setState(CellState.KILLED);
        fr.repaint();
    }
}