package network.subjectStates;

import network.serverClasses.User;
import observer.multi.MultiSubject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Илья on 09.05.2016.
 */
public class ConnectState implements State {
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public void doAction(MultiSubject subject) {
        System.out.println("Connect state");
        User user = subject.getUser();
        subject.writeToLog(user.toString());
        int PORT = Integer.parseInt(user.getPort());
        try {
            Socket socket = new Socket(user.getServerIp(), PORT);
            subject.socket = socket;
            in = subject.in;
            out = subject.out;

            subject.out = new ObjectOutputStream(socket.getOutputStream());
            subject.writeToLog("socket output is ready, I suppose");
            subject.out.flush();
            subject.in = new ObjectInputStream(socket.getInputStream());

            subject.out.writeObject(user.getName());
            // Запускаем вывод всех входящих сообщений в консоль
            subject.getObserver().createResender();
            System.out.println("lel");
            subject.setGameState(new StartState());
            // Пока пользователь не введёт "exit" отправляем на сервер всё, что
            // введено в консоль
            /*
            Object str = "";
            while (!str.equals("exit")) {
                //str = scan.nextLine();
                out.writeObject(str.toString());
                out.flush();
            }
            resend.setStop();
*/
        } catch (ConnectException e) {
            System.out.println("Connection was interrupted");
        } catch (IOException e) {
            e.printStackTrace();
        }
        //finally {
        //    close();
        //}
    }

    public void doReceive(MultiSubject subject, Object object){
        System.out.println("Заглушка в ConnectState");
    };

    /**
     * Закрывает входной и выходной потоки и сокет
     */
    private void close() {
        try {
            in.close();
            out.close();
            //socket.close();
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }
}
