package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.ui.ChatUI;
import main.java.fr.mrc.ptichat.ui.ConnectionUI;

public class AppManager {

    private ConnectionManager connectionManager;
    private ConnectionUI connectionUI;
    private ChatUI chatUI;


    public AppManager() {
        this.connectionManager = new ConnectionManager(this);
        this.connectionUI = new ConnectionUI(connectionManager);
        this.chatUI = new ChatUI();
    }

    public void init() {
        this.connectionUI.open();
    }

    public void initChat(String m) {
        System.out.println(m);
        this.connectionUI.dispose();
        this.chatUI.open();
    }

    public void handleConnectionError(String m){
        this.connectionUI.addErrorMessage(m);
    }

}
