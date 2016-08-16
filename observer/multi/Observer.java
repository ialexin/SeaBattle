package observer.multi;

/**
 * Created by Илья on 09.05.2016.
 */
public abstract class Observer {
    protected MultiSubject subject;
    public abstract void update();
}
