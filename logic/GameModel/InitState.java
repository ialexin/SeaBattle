package logic.GameModel;

import logic.Data;
import logic.FieldModel;
import logic.enums.State;

import java.awt.*;

/**
 * Created by Илья on 10.04.2016.
 */
public class InitState implements GameState{
    private int ID  = 1;
    State state = State.INITIAL;

    public void doAction(Context context){
        System.out.println("Initializing stage");
        context.writeToLog("Initialize stage" + "\n");
        doInit(context.getPlayerField());
        doInit(context.getEnemyField());

        System.out.println(context.getPlayerField().getCurShipsAmount());
        System.out.println("COMRAAAAD");
        context.getView().repaint();
    }

    public void doInit(FieldModel model){
        model.setAllShips();
        System.out.println("The Field is ready");
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return "Initialize";
    }
}
