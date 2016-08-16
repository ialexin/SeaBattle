package observer.single;

import logic.GameModel.Context;
import observer.single.Observer;
import observer.single.SingleSubject;

/**
 * Created by Илья on 21.04.2016.
 */
public class SingleObserver extends Observer {
    Context context;

    public SingleObserver(SingleSubject subject, Context context){
        this.context = context;
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update(){
        context.setState(subject.getGameState());
        context.getState().doAction(context);
        System.out.println("Observer at work");
    }
}
