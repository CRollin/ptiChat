package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.processing.RequestHandler;

import java.io.PrintWriter;
import java.net.InetAddress;

/**
 * Handles the messages to send
 */
public class ResponseRunnable implements Runnable {

    private InetAddress address;
    private int port;
    private PrintWriter out;
    private Input input = new Input();
    private RequestHandler rh = new RequestHandler();
    private Flag stopFlag;

    public ResponseRunnable(PrintWriter out, InetAddress address, int port, Flag stopFlag){
        this.address = address;
        this.out = out;
        this.port = port;
        this.stopFlag = stopFlag;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        String message;
        while(!this.stopFlag.getFlag()) {
            System.out.println("Type a message to " + this.address + ":" + this.port +  " : ");
            message = this.input.getInput();
            this.out.println(message);
            this.out.flush();
            if (rh.isTerminationMessage(message)) this.stop();
        }
        this.out.close();
    }

    private void stop(){
        this.stopFlag.setFlag(true);
    }
}
