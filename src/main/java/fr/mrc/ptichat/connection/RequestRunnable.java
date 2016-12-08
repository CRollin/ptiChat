package main.java.fr.mrc.ptichat.connection;
import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.processing.MessageHandler;


import java.io.*;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Handles the messages to receive
 */
public class RequestRunnable implements Runnable {

    private BufferedReader in;
    private InetAddress address;
    private int port;
    private ChatManager chatManager;
    private MessageHandler mh = new MessageHandler();
    private Flag stopFlag;

    public RequestRunnable(BufferedReader in, InetAddress address, int port, Flag stopFlag, ChatManager chatManager){
        this.in = in;
        this.address = address;
        this.port = port;
        this.stopFlag = stopFlag;
        this.chatManager = chatManager;
    }

    public void run(){
        // TODO: when linking this code to the UI, remove the "System.out.println()" function
        String message;
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        while(!this.stopFlag.getFlag()){
            try {
                if (this.in.ready()) {
                    message = this.in.readLine();
                    if (mh.isTerminationMessage(message)) {
                        message = "User left the chat, disconnecting...";
                        this.stop();
                    } else if (mh.isFileTransmission(message)){
                        try {
                            String[] messageParts = mh.getContentFromMessage(message);
                            message = mh.messageToFile(messageParts[1], messageParts[2]);
                        } catch (IOException e) {
                            message = e.getMessage();
                        }
                    }
                    String intro = "\n>>>>>>" + this.address + ":" + this.port + " à " + sdf.format(cal.getTime()) + "\n";
                    String received = intro + message + "\n";
                    this.chatManager.receivedMessage(received);
                }
            } catch (IOException e){
                // System.out.println("An error occurred");
                e.printStackTrace();
            }
        }
        try {
            // System.out.println("Closing reader");
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Disconnected");
    }

    public void stop() { this.stopFlag.setFlag(true); }

}
