package observer.single;

import logic.GameModel.GameState;
import observer.single.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Илья on 20.04.2016.
 */
public class SingleSubject {
    private List<Observer> observers = new ArrayList<>();
    private int state;
    private GameState game_state;

    public int getState() {
        return state;
    }

    public GameState getGameState(){
        return game_state;
    }

    public void setGameState(GameState state){
        this.game_state = state;
        notifyAllObservers();
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
        }
    }


}
