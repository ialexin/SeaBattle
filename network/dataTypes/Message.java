package network.dataTypes;

/**
 * Created by Илья on 15.05.2016.
 */
public class Message extends Information{
    String message;

    public Message(String str){
        message = str;
    }

    public boolean isMessage(){
        return true;
    }
}
