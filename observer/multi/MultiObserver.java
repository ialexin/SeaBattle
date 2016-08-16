package observer.multi;

import java.io.IOException;

/**
 * Created by Илья on 09.05.2016.
 */
public class MultiObserver extends Observer{

    public MultiObserver(MultiSubject subject){
        this.subject = subject;
        this.subject.attach(this);
    }

    public void update(){
        subject.getGameState().doAction(subject);
    }

    public void createResender(){
        new Resender();
    }

    private class Resender implements Runnable{
        private Thread resendThread;

        public Resender(){
            resendThread = new Thread(this, "Resender");
            System.out.println("The second thread was created: " + resendThread);
            resendThread.start();
        }

        public void run(){
            try {
                System.out.println("sign");
                Object str;
                while (true) {
                    //используй if c -1 вместо available())
                    //int check;
                    //if((check = in.read()) != -1) {
                    subject.out.flush();
                    str = subject.in.readObject();
                    if(str.getClass().toString().equals("class java.lang.String")) {
                        System.out.println(str.toString());
                        subject.writeToLog(str.toString());
                    }
                    else{
                        //subject.getGameState().doReceive(subject, str);
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка при получении сообщения.");
                //System.out.println("socket is Closed: " + socket.isClosed());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Error in Client run()");
            }
        }
    }
}
