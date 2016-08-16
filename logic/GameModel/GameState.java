package logic.GameModel;


import logic.enums.State;

/**
 * Created by Илья on 10.04.2016.
 */
public interface GameState {
    void doAction(Context context);
    // doMessage(Context context);
    int getID();
}
