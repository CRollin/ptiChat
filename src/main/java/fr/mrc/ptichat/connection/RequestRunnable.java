package main.java.fr.mrc.ptichat.connection;
import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.processing.MessageHandler;
import main.java.fr.mrc.ptichat.utils.LanguagesController;


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
    private LanguagesController languagesController = LanguagesController.getInstance();

    public RequestRunnable(BufferedReader in, InetAddress address, int port, Flag stopFlag, ChatManager chatManager){
        this.in = in;
        this.address = address;
        this.port = port;
        this.stopFlag = stopFlag;
        this.chatManager = chatManager;
    }

    public void run(){
        String message;
        while(!this.stopFlag.getFlag()){
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            try {
                if (this.in.ready()) {
                    message = this.in.readLine();
                    String intro = "\n>>>>>> " + languagesController.getWord("RECEIVED_AT") + " " + sdf.format(cal.getTime()) + "\n";
                    if (mh.isTerminationMessage(message)) {
                        this.stop();
                    } else if (mh.isFileTransmission(message)){
                        try {
                            String[] messageParts = mh.getContentFromMessage(message);
                            message = intro + mh.messageToFile(messageParts[1], messageParts[2]) + "\n";
                        } catch (IOException e) {
                            message = e.getMessage();
                        }
                    } else {
                        message = intro + message + "\n";
                    }
                    this.chatManager.receivedMessage(message);
                }
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        try {
            this.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() { this.stopFlag.setFlag(true); }

}
