package network.serverClasses;

import logic.FieldModel;
import network.Server;

import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

/**
 * Created by Илья on 24.04.2016.
 */
public class SBProtocol {
    private static final int CONNECTING = 0;
    private static final int START = 1;
    private static final int INIT = 2;
    private static final int READINESS = 2;
    private int state;
    private boolean readiness;
    private Connection connection;
    private ObjectOutputStream output;

    SBProtocol(Connection connection) {
        state = CONNECTING;
        readiness = false;
        this.connection = connection;
    }

    public String processInput(String input) {
        String output = "";

        //try {
            switch (state) {
                case 0:
                    output = "Waiting for your opponent. We can't start now, bro";
                    break;
                case 1:
                    if (input.equals("Start")) {
                        if (readiness)
                            output = "Actually you don't have to press Start again";
                        else {
                            readiness = true;
                            output = "Okey. Prepare to initialize field";
                            System.out.println(connection.getPlayerName() + " is ready");
                        }
                    }else{
                            if (!readiness)
                                output = "Press Start button man";
                            else
                                output = "You are ready. We remember";
                        }
                    break;
                case 2:
                    FieldModel model = connection.getServer().getContext().getFieldByConnection(connection);
                    if (input.equals("Generation")) {
                        model.setAllShips();
                    }
                    break;

                default:
                    output = "gi";
                    break;
            }
        //}catch(IOException e){
        //    System.out.println("I suppose model == null");
        //}
        return output;
    }

    public boolean isReady(){
        return readiness;
    }

    public void setState(int x){
        state = x;
    }
}
