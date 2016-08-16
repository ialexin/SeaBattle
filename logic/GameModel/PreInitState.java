package logic.GameModel;

import logic.enums.State;

/**
 * Created by Илья on 21.04.2016.
 */
public class PreInitState implements GameState{
    private int ID  = 2;
    State state = State.PRE_INITIAL;

    public void doAction(Context context){

    }

    public int getID(){
        return ID;
    }
}
