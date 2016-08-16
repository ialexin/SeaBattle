package observer;

import observer.single.SingleSubject;

/**
 * Created by Илья on 20.04.2016.
 */
public class Demo {
    public static void main(String[] args) {
        SingleSubject subject = new SingleSubject();

        //new HexaObserver(subject);
        //new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }
}
