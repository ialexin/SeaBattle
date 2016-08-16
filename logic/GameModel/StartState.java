package logic.GameModel;


import logic.enums.State;

/**
 * Created by Илья on 10.04.2016.
 */
public class StartState implements GameState{
    private int ID = 0;

    public void doAction(Context context){
        context.getView().log.textArea.append("State is : " + toString() + "\n");
        System.out.println("State is : " + toString());
        context.setState(new InitState());
    }

    public int getID(){
        return ID;
    }

    public String toString(){
        return "Start";
    }
}
