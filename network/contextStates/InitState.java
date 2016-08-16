package network.contextStates;

import network.Server;
import network.serverClasses.Connection;
import network.serverClasses.Context;

import java.util.Iterator;

/**
 * Created by Илья on 25.04.2016.
 */
public class InitState implements State{
    public void doAction(Context context){
        System.out.println("hell yeah");
        Iterator<Connection> iter = Server.connections.iterator();
        while(iter.hasNext()){
            iter.next().getSBProtocol().setState(2);
        }
    }
}
