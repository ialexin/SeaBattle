package network.dataTypes;


/**
 * Created by Илья on 09.05.2016.
 */
public abstract class Information {
    private Object object;

    public Information(){};

    public Information(Object obj){
        object = obj;
    }

    public String getType(){
        return "Information";
    }

    public Object getInformation(){
        return object;
    }

    public Object dispatch(){
        return object;
    }
}
