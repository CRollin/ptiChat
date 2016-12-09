package main.java.fr.mrc.ptichat.connection;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.processing.MessageHandler;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

/**
 * Handles the messages to send
 */
public class ResponseRunnable implements Runnable {

    private InetAddress address;
    private int port;
    private PrintWriter out;
    private MessageHandler mh = new MessageHandler();
    private Flag stopFlag;
    private ChatManager chatManager;
    private LanguagesController languagesController = LanguagesController.getInstance();

    public ResponseRunnable(PrintWriter out, InetAddress address, int port, Flag stopFlag, ChatManager chatManager){
        this.address = address;
        this.out = out;
        this.port = port;
        this.stopFlag = stopFlag;
        this.chatManager = chatManager;
    }

    public void run(){
        String message;
        while(!this.stopFlag.getFlag()) {
            message = this.chatManager.getMessage();
            try {
                if (mh.isTerminationMessage(message)) {
                    this.stop();
                } else if(mh.isFileTransmission(message)){
                    String[] messageParts = mh.getContentFromMessage(message);
                    String file = mh.fileToString(messageParts[1]);
                    message = message + " " + file;
                    this.chatManager.receivedMessage(this.languagesController.getWord("FILE_SENT"));
                }
                this.out.println(message);
                this.out.flush();
            } catch (IOException ioe) {
                this.chatManager.receivedMessage(ioe.getMessage());
            }
        }
        this.out.close();
    }

    private void stop(){
        this.stopFlag.setFlag(true);
    }
}
