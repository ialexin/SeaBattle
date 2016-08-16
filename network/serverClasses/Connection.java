package network.serverClasses;

import network.Server;

import java.io.*;
import java.net.Socket;
import java.util.Iterator;

/**
 * Created by Илья on 23.04.2016.
 */
public class Connection extends Thread {
    public ObjectInputStream in;
    public ObjectOutputStream out;
    //public BufferedReader in;
    //public PrintWriter out;
    private Socket socket;
    private Server server;
    private SBProtocol protocol;

    private String name = "";

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
        this.protocol = new SBProtocol(this);

        try {
            //out = new PrintWriter(socket.getOutputStream(), true);
            socket.getInputStream();
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());
            //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out.writeObject("Check");
        } catch (IOException e) {
            e.printStackTrace();
            close();
        }
    }

    @Override
    public void run() {
        try {
            out.flush();
            name = in.readObject().toString();
            // Отправляем всем клиентам сообщение о том, что зашёл новый пользователь
            synchronized(Server.connections) {
                Iterator<Connection> iter = Server.connections.iterator();
                while(iter.hasNext()) {
                    Connection con = iter.next();
                    System.out.println(name + " came now");
                    con.out.writeObject(name + " came now");
                    //con.out.flush();
                }
            }
            Object str;
            while (true) {
                //походу ошибка в readLine() - замени на костыльный объект
                str = in.readObject();
                System.out.println(str.getClass().toString());
                if(str.getClass().toString().equals("class java.lang.String")){
                    System.out.println(str);
                    if (str.equals("exit"))
                        break;
                    // Отправляем всем клиентам очередное сообщение
                    synchronized (Server.connections) {
                        Iterator<Connection> iter = Server.connections.iterator();
                        while (iter.hasNext()) {
                            Connection con = iter.next();
                            if (!con.equals(this))
                                con.out.writeObject(name + ": " + str);
                            //con.out.flush();
                        }
                    }
                    out.writeObject(protocol.processInput(str.toString()));
                }
                else
                    out.writeObject("else");
                //out.flush();
            }
            synchronized(Server.connections) {
                Iterator<Connection> iter = Server.connections.iterator();
                while(iter.hasNext()) {
                    (iter.next()).out.writeObject(name + " has left");
                }
            }
        } catch (IOException e) {
            System.out.println("some of IOExceptions. Connection interrupted");
       }
        catch(ClassNotFoundException e){
            System.out.println("Class Not found Exception");
            e.printStackTrace();
        }
        finally{
            close();
            Server.connections.remove(this);
        }
    }

    public void close(){
        try{
            in.close();
            out.close();
            socket.close();
            server.closeAll();
            System.exit(0);
        }
        catch(IOException e){
            System.err.println("Stream were not closed");
        }
    }

    public SBProtocol getSBProtocol(){
        return protocol;
    }

   public String getPlayerName(){
        return name;
    }

    public Server getServer(){
        return server;
    }

    /*public ObjectOutputStream getObjectOutput(){
        return output;
    }*/
}
