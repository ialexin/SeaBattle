package observer;

import observer.single.Observer;
import observer.single.SingleSubject;

/**
 * Created by Илья on 20.04.2016.
 */
public class BinaryObserver extends Observer {
    public BinaryObserver(SingleSubject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update(){
        System.out.println("Binary String: " + Integer.toBinaryString((subject.getState())));

    }
}
