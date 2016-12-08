package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.processing.MessageHandler;
import main.java.fr.mrc.ptichat.ui.ChatUI;

public class ChatManager {

    private AppManager am;
    private String messageToSend;
    private ChatUI chatUI;
    public MessageHandler mh = new MessageHandler();

    public ChatManager(AppManager am) {
        this.am = am;
    }

    public void create(String peerIp){
        this.chatUI = new ChatUI(this, peerIp);
    }

    public void open(){
        this.chatUI.open();
    }

    public void leaveChat() {
        this.chatUI.dispose();
        this.am.leaveChat();
    }

    public void receivedMessage(String m) {
        if(mh.isTerminationMessage(m)){
            this.chatUI.handlePeerDeconnection();
        } else {
            this.chatUI.addMessage(m);
        }
    }

    public String getMessage(){
        String m = "";
        try {
            while(this.messageToSend == null) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // System.out.println("Input interrupted!");
                    return null;
                }
            }
            m = this.messageToSend;
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.setMessage(null);
        return m;
    }

    public void setMessage(String m){
        this.messageToSend = m;
    }


}
