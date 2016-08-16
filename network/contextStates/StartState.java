package network.contextStates;

import network.Server;
import network.serverClasses.Connection;
import network.serverClasses.Context;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by Илья on 25.04.2016.
 */
public class StartState implements State{

    public void doAction(Context context){
        Iterator<Connection> iter = Server.connections.iterator();
        try {
            while (iter.hasNext()) {
                iter.next().out.writeObject("Game is ready to start.\n" +
                        "Input 'Start' when you will be ready to begin the Battle");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        iter = Server.connections.iterator();

        while(iter.hasNext())
            iter.next().getSBProtocol().setState(1);

        while(!context.conA.getSBProtocol().isReady() ||
                !context.conB.getSBProtocol().isReady()){
            //you can write smth here
        }
        context.setState(new InitState());
    }
}
