package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.ui.ChatUI;

import java.io.IOException;

public class ChatManager {

    private AppManager am;
    private String messageToSend;
    private ChatUI chatUI;

    public ChatManager(AppManager am) {
        this.am = am;
    }

    public void create(String peerIp){
        this.chatUI = new ChatUI(this, peerIp);
    }

    public void open(){
        this.chatUI.open();
    }

    public void receivedMessage(String m) {
        this.chatUI.addMessage(m);
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
