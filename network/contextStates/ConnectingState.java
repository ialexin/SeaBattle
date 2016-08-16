package network.contextStates;

import network.Server;
import network.serverClasses.Connection;
import network.serverClasses.Context;

import java.util.Iterator;

/**
 * Created by Илья on 25.04.2016.
 */
public class ConnectingState implements State{
    public void doAction(Context context){
        Iterator<Connection> iter = Server.connections.iterator();
        while(iter.hasNext())
            iter.next().getSBProtocol().setState(0);
    }
}
