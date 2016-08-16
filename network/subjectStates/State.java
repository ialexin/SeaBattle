package network.subjectStates;

import observer.multi.MultiSubject;

/**
 * Created by Илья on 09.05.2016.
 */
public interface State{
    void doAction(MultiSubject subject);

    //void doReceive(MultiSubject subject, Object object);
}
