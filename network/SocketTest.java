package network;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Илья on 19.04.2016.
 */
public class SocketTest {
    public static void main(String[] args) throws IOException {
        String host = "www.google.com";
        System.out.println(InetAddress.getLocalHost());
        InetAddress[] adresses = InetAddress.getAllByName(host);
        for(InetAddress a : adresses) {
            System.out.println(a);
        }
    }
}
