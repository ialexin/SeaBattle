package network;

import network.contextStates.ConnectingState;
import network.contextStates.StartState;
import network.serverClasses.Connection;
import network.serverClasses.Context;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Илья on 19.04.2016.
 */
public class Server {
    public static List<Connection> connections; //= new ArrayList<Connection>();
    private ServerSocket server;
    private Context context;

    public Server() {
        try {
            server = new ServerSocket(Const.PORT);
            connections = new ArrayList<Connection>();
            context = new Context();
            context.setState(new ConnectingState());
            System.out.println("Server started");
            while (connections.size() < 2) {
                Socket socket = server.accept();
                if(connections.size() < 2) {
                    Connection con = new Connection(socket, this);
                    if(connections.size() < 1)

                        System.out.println("Waiting for opponent");
                    connections.add(con);
                    con.out.writeObject("Welcome, comrade");
                    con.start();
                }
                else {
                    try {
                       //BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                       //PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        out.writeChars("Sorry bro. The Battle has already started");
                        out.writeChars("Connection was interrupted");
                    } catch (IOException e) {
                        e.printStackTrace();
                        socket.close();
                    }
                }
            };
            context.setFieldsByConnection();
            context.setConA(connections.get(0));
            context.setConB(connections.get(1));
            //context.conA.start();
            //context.conB.start();
            System.out.println("Here");

            context.setState(new StartState());
        } catch (IOException e) {
            System.out.println("IOException.Connection lost");
        }
        finally
        {
            System.out.println("closeAll stage");
            closeAll();
        }
    }

    public void closeAll() {
        try {
            server.close();
            synchronized(connections) {
                Iterator<Connection> iter = connections.iterator();
                while(iter.hasNext()) {
                    ((Connection) iter.next()).close();
                }
            }
        } catch (IOException e) {
            System.err.println("Streams were not closed");
        } catch(NullPointerException e){
            System.out.println("Port is employed.\nTry Later or use another port");
        }
    }

    public Context getContext(){
        return context;
    }
    /*
    private class Connection extends Thread {
        private BufferedReader in;
        private PrintWriter out;
        private Socket socket;

        private String name = "";

        public Connection(Socket socket) {
            this.socket = socket;

            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
                close();
            }
        }

        @Override
        public void run() {
            try {
                name = in.readLine();
                // Отправляем всем клиентам сообщение о том, что зашёл новый пользователь
                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + " cames now");
                    }
                }

                String str = "";
                while (true) {
                    str = in.readLine();
                    if(str.equals("exit"))
                        break;

                    // Отправляем всем клиентам очередное сообщение
                    synchronized(connections) {
                        Iterator<Connection> iter = connections.iterator();
                        while(iter.hasNext()) {
                            ((Connection) iter.next()).out.println(name + ": " + str);
                        }
                    }
                }

                synchronized(connections) {
                    Iterator<Connection> iter = connections.iterator();
                    while(iter.hasNext()) {
                        ((Connection) iter.next()).out.println(name + " has left");
                    }
                }
            } catch (IOException e) {
                System.out.println("some of IOExceptions. Connection interrupted");
            }
            finally{
                close();
                connections.remove(this);
            }
        }

        public void close(){
            try{
                in.close();
                out.close();
                socket.close();
                Server.this.closeAll();
                System.exit(0);
            }
            catch(IOException e){
                System.err.println("Stream were not closed");
            }
        }
    }*/
}

