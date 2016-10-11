package fr.mrc.ptichat.connexion;

import java.io.*;
import java.net.*;

public class Chat implements Runnable {

    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private Thread requestThread, responseThread;

    public Chat(Socket socket){
        this.socket = socket;
    }

    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            requestThread = new Thread(new Request(in));
            requestThread.start();
            responseThread = new Thread(new Response(out));
            responseThread.start();
        } catch(IOException e){
            System.err.println("Someone just disconnected from ptit Chat.");
        }
    }
}
