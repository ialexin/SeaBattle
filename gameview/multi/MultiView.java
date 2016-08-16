package gameview.multi;

import gameview.AboutDialog;
import gameview.single.SingleView;
import logic.FieldModel;
import network.serverClasses.User;
import network.subjectStates.ConnectState;
import observer.multi.MultiSubject;

import javax.swing.*;

/**
 * Created by Илья on 08.05.2016.
 */
public class MultiView extends SingleView{
    //private static Map<CellState, Color> colors;
    //public static FieldModel player;
    //public static FieldModel enemy;

    private ConnectDialog dialog = null;
    //private AboutDialog aboutDialog;
    public MultiSubject subject;
    public LogMulti logg;
    public JPanel buttonPanel;
    //public JPanel fieldEN;
    //public JPanel fieldPL;
    //public JButton starter;

    public MultiView(FieldModel player, FieldModel enemy){
        super(player, enemy);

        //this.player = player;
        //this.enemy = enemy;

        super.getContentPane().remove(super.log);
        subject = new MultiSubject(this);
        logg = new LogMulti(subject);

        //starter = new StartButton();
       // buttonPanel = new JPanel();


        //getContentPane().setLayout(null);

        //colors = new HashMap<>();
        //setHashMap();

        createMenu();

        //buttonPanel.setLocation(500, 800);
        logg.setLocation(800, 200);

        //getContentPane().setLayout(null);
        getContentPane().add(logg);
        //setVisible(true);
        //setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //setSize(1200, 960);
    }

    private void createMenu(){
        JMenuBar mbar = new JMenuBar();
        setJMenuBar(mbar);
        JMenu fileMenu = new JMenu("File");

        mbar.add(fileMenu);

        JMenuItem connectItem = new JMenuItem("Connect");
        fileMenu.add(connectItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        fileMenu.add(exitItem);


        connectItem.addActionListener(event -> {
            subject.writeToLog("plez");
            if(dialog == null)
                dialog = new ConnectDialog();
            dialog.getOKButton().addActionListener(ev -> {
                dialog.getDialog().setVisible(false);
                subject.writeToLog("DoDo");
                subject.setUser(new User(dialog.getUsername(), dialog.getIp(), dialog.getPort()));
                for(JTextField field : dialog.getTextFields())
                    field.setEnabled(false);
                subject.setGameState(new ConnectState());
            });
            if (dialog.showDialog(this, "Connect"))
                //if user verified inputted data
                //extract if from following processing
                System.out.println(dialog.getUsername() + "  " + dialog.getIp() + ": " + dialog.getPort());
        });

        exitItem.addActionListener( event -> {
            System.exit(0);
        });


        JMenuItem aboutItem = new JMenuItem("About");
        aboutItem.addActionListener(event -> {
            if(aboutDialog == null)
                aboutDialog = new AboutDialog(this);
            else
                aboutDialog.setVisible(true);
        });
        JMenu aboutMenu = new JMenu("About");
        aboutMenu.add(aboutItem);
        mbar.add(aboutMenu);
    }

    //написано в SingleView
   /* private static void setHashMap() {
        colors.put(CellState.NON_STATED, Color.LIGHT_GRAY);
        colors.put(CellState.WATER, Color.BLUE);
        colors.put(CellState.DECK, Color.GRAY);
        colors.put(CellState.KILLED, Color.RED);
        colors.put(CellState.MISSED, Color.PINK);
    }




    private class StartButton extends JButton{
        public StartButton() {
            setSize(130, 60);
            setText("StartGame");
            setFont(new Font("Serif", Font.ITALIC, 20));
            setFocusable(false);
            setVisible(true);
            addActionListener(event -> {
                //сделать ActionListener()
            });
        }
    }
    */
}
