package observer.multi;

import gameview.multi.MultiView;
import network.serverClasses.User;
import network.subjectStates.State;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Илья on 09.05.2016.
 */
public class MultiSubject {
    private MultiObserver observer;
    private int state;
    private State gameState;
    private User user;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public Socket socket;
    private MultiView view;

    public MultiSubject(MultiView view){
        super();
        observer = new MultiObserver(this);
        this.view = view;
    }

    public State getGameState(){
        return gameState;
    }

    public void setGameState(State state){
        this.gameState = state;
        notifyObserver();
    }

    public void attach(MultiObserver observer){
        this.observer =  observer;
    }

    public void notifyObserver(){
        writeToLog("notify Observers");
        observer.update();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void writeToLog(String str){
        view.logg.textArea.append(str + "\n");
    }

    public MultiObserver getObserver(){
        return observer;
    }

}
