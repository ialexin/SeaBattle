package logic.GameModel;

import logic.FieldModel;
import logic.Vector;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Илья on 22.04.2016.
 */
public class ManInitState extends InitState implements GameState{
    public static int count = 0;
    Vector vctor = new Vector(0, 0);
    int row = 1;
    int col = 1;

    @Override
    public void doAction(Context context){
        System.out.println("Manual initiaizing stage");
        doInit(context.getEnemyField());


        context.getView().fieldPL.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                count++;
                if(count % 2 == 1) {
                    col = context.getView().convertCol(e.getX());
                    row = context.getView().convertRow(e.getY());
                    System.out.println("The cell is: " + col + ", " + row);
                }
                else{

                }
            }
        });
    }
}
