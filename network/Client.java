package network;

import gameview.multi.MultiView;
import logic.FieldModel;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Илья on 19.04.2016.
 */
public class Client {
    //private BufferedReader in;
    //private PrintWriter out;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private Socket socket;

    /**
     * Запрашивает у пользователя ник и организовывает обмен сообщениями с
     * сервером
     */
    public Client() {
        Scanner scan = new Scanner(System.in);
        EventQueue.invokeLater(new Runnable(){
            public void run() {
                JFrame frame = new MultiView(new FieldModel(), new FieldModel());
            }
        });

        System.out.println("Введите IP для подключения к серверу.");
        System.out.println("Формат: xxx.xxx.xxx.xxx");

        String ip = scan.nextLine();

        try {
            // Подключаемся в серверу и получаем потоки(in и out) для передачи сообщений
            socket = new Socket(ip, Const.PORT);
            //in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //out = new PrintWriter(socket.getOutputStream(), true);
            out = new ObjectOutputStream(socket.getOutputStream());
            System.out.println("socket output is ready, I suppose");
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

            System.out.println("Введите свой ник:");
            out.writeObject(scan.nextLine());

            // Запускаем вывод всех входящих сообщений в консоль
            Resender resend = new Resender();
            resend.start();

            // Пока пользователь не введёт "exit" отправляем на сервер всё, что
            // введено из консоли
            Object str = "";
            while (!str.equals("exit")) {
                str = scan.nextLine();
                out.writeObject(str.toString());
                out.flush();
            }
            resend.setStop();
        } catch (ConnectException e) {
            System.out.println("Connection was interrupted");
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            close();
        }
    }

    /**
     * Закрывает входной и выходной потоки и сокет
     */
    private void close() {
        try {
            in.close();
            out.close();
            socket.close();
        } catch (Exception e) {
            System.err.println("Потоки не были закрыты!");
        }
    }

    /**
     * Класс в отдельной нити пересылает все сообщения от сервера в консоль.
     * Работает пока не будет вызван метод setStop().
     *
     * @author Илья Алексин
     */
    private class Resender extends Thread{

        private boolean stoped;

        /**
         * Прекращает пересылку сообщений
         */
        public void setStop() {
            stoped = true;
        }

        /**
         * Считывает все сообщения от сервера и печатает их в консоль.
         * Останавливается вызовом метода setStop()
         *
         * @see java.lang.Thread#run()
         */
        @Override
        public void run() {
            try {
                System.out.println("sign");
                Object str;
                while (!stoped){

                    //используй if c -1 вместо available())
                    //int check;
                    //if((check = in.read()) != -1) {
                        out.flush();
                        str = in.readObject();
                        System.out.println(str.toString());
                    //}
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                System.out.println("socket is Closed: " + socket.isClosed());
                e.printStackTrace();
            } catch (ClassNotFoundException e){
                System.err.println("Error in Client run()");
            }
            /*finally{
                try {
                    System.out.println(in.readUTF());
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }*/
        }
    }
}
