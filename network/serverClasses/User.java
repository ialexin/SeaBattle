package network.serverClasses;

/**
 * Created by Илья on 23.04.2016.
 */
public class User {
    private String name;
    private String serverIp;
    private String serverPort;


    public User(String name, String serverIp, String port){
        this.name = name;
        this.serverIp = serverIp;
        this.serverPort = port;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getPort() {
        return serverPort;
    }

    public void setPort(String port) {
        this.serverPort = port;
    }

    public String toString(){
        return "nick: " + name + " ip: " + serverIp + ":" + serverPort;
    }
}
