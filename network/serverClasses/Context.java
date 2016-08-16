package network.serverClasses;

import logic.FieldModel;
import network.Server;
import network.contextStates.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Илья on 23.04.2016.
 */
public class Context {
    private State state;
    private Map<Connection, FieldModel> fields;
    public Connection conA;
    public Connection conB;

    public Context(){
        fields = new HashMap<>();
    }

    public void setConA(Connection connection){
        conA = connection;
    }

    public void setConB(Connection connection){
        conB = connection;
    }

    public State getState(){
        return state;
    }

    public FieldModel getFieldByConnection(Connection connection){
        return fields.get(connection);
    }

    public void setFieldsByConnection(){
        Iterator<Connection> iter = Server.connections.iterator();
        while(iter.hasNext())
            fields.put(iter.next(), new FieldModel());
    }
    public void setState(State state){
        this.state = state;
        this.getState().doAction(this);
        //переписать GameState и все классы???
    }
}
