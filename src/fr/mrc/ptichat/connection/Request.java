package fr.mrc.ptichat.connection;

import java.io.*;

public class Request implements Runnable {

    private BufferedReader in;
    private String message = null;

    public Request(BufferedReader in){
        this.in = in;
    }

    public void run(){
        while(true){
            try {
                message = in.readLine();
                System.out.println("Received : " + message);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

}
