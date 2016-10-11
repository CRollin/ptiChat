package fr.mrc.ptichat.connexion;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.net.*;

public class AcceptConnexions implements Runnable {

    public Thread chatThread;
    private ServerSocket serverSocket = null;
    private Socket socket = null;

    public AcceptConnexions(ServerSocket ss){
        this.serverSocket = ss;
    }

    @Override
    public void run() {
        try {
            while(true){
                socket = serverSocket.accept();
                System.out.println("Someone wants to connect to you.");
                chatThread = new Thread(new Chat(socket));
                chatThread.start();
            }
        } catch (IOException e){
            System.err.println("Server Error");
        }
    }
}
