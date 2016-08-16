package logic.GameModel;

import logic.FieldModel;
import logic.enums.State;

/**
 * Created by Илья on 11.04.2016.
 */
public class OverState implements GameState{
    private int ID  = 5;
    State state = State.OVER;

    public void doAction(Context context){
        context.getView().log.textArea.append("State is : " + toString());
        context.getView().log.textArea.append("Actually game is over");
        System.out.println("Game is over");
        context.setState(this);
    }

    public void doResult(FieldModel player, FieldModel enemy){
        if (player.fleetIsSunk())
            System.out.println("The winner is " + enemy.getNickName());
        else
            System.out.println("The winner is " + player.getNickName());
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return "Actually game is over";
    }
}
