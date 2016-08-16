package observer.single;

/**
 * Created by Илья on 20.04.2016.
 */
public abstract class Observer {
    protected SingleSubject subject;
    public abstract void update();
}
