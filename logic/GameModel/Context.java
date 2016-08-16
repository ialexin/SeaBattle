package logic.GameModel;

import gameview.single.SingleView;
import logic.FieldModel;

/**
 * Created by Илья on 10.04.2016.
 */
public class Context {
    public static int stateConst = 0;
    private GameState state;
    private FieldModel player;
    private FieldModel enemy;
    private SingleView view;

    public Context(FieldModel player, FieldModel enemy) {
        state = new StartState();
        this.player = player;
        this.enemy = enemy;
        view = new SingleView(player, enemy);
    }

    public SingleView getView(){
        return view;
    }

    public void setState(GameState state) {
        this.state = state;
    }

    public GameState getState(){
        return state;
    }

    public FieldModel getPlayerField(){
        return player;
    }

    public FieldModel getEnemyField(){
        return enemy;
    }

    public void writeToLog(String str){
        view.log.textArea.append(str + "\n");
    }
}

