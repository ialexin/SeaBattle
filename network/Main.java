package network;

import java.util.Scanner;

/**
 * Created by Илья on 19.04.2016.
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);


        System.out.println("Запустить программу в режиме сервера или клиента? (S(erver) / C(lient))");
        while (true) {
            char answer = Character.toLowerCase(in.nextLine().charAt(0));
            if (answer == 's') {
                new Server();
                break;
            } else if (answer == 'c') {
                new Client();
                break;
            } else {
                System.out.println("Некорректный ввод. Повторите.");
            }
        }
    }
}
