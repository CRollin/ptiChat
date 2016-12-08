package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
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
    //private Input input = new Input();
    private RequestHandler rh = new RequestHandler();
    private Flag flag;
    private ChatManager chatManager;

    public ResponseRunnable(PrintWriter out, InetAddress address, int port, Flag flag, ChatManager chatManager){
        this.address = address;
        this.out = out;
        this.port = port;
        this.flag = flag;
        this.chatManager = chatManager;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        String message;
        while(!this.flag.getFlag()) {
            //System.out.println("Type a message to " + this.address + ":" + this.port +  " : ");
            message = this.chatManager.getMessage();//this.input.getInput();
            this.out.println(message);
            this.out.flush();
            if (rh.isTerminationMessage(message)) this.stop();
        }
        this.out.close();
    }

    private void stop(){
        this.flag.setFlag(true);
    }
}
