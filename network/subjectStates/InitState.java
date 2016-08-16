package network.subjectStates;

import observer.multi.MultiSubject;

import java.io.IOException;

/**
 * Created by Илья on 15.05.2016.
 */
public class InitState implements State{
    public void doAction(MultiSubject subject){
        try {
            subject.out.flush();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void doReceive(MultiSubject subject, Object object){

    }
}
