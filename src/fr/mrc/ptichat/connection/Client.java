package fr.mrc.ptichat.connection;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static Socket socket = null;
    public static Thread chatThread;

    public static void initiateClientSocket(){
        try {
            System.out.println("\n###### Client's socket Initialisation ######");
            Scanner sc = new Scanner(System.in);
            System.out.println("Host IP address (ex: 127.0.0.1) : ");
            String host = sc.nextLine();
            System.out.println("Port (ex: 9118): ");
            int port = sc.nextInt();
            socket = new Socket(host, port);
            // Chat
            chatThread = new Thread(new Chat(socket));
            chatThread.start();
        } catch (UnknownHostException e){
            System.err.println("connection to " + socket.getLocalAddress() + " impossible");
        } catch (IOException e){
            System.err.println("The port " + socket.getLocalPort() + " is already used.");
        }
    }
}
