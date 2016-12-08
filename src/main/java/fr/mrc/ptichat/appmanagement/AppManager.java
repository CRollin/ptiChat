package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

public class AppManager {

    private ConnectionManager connectionManager;
    private ChatManager chatManager;
    private LanguagesController languagesController = LanguagesController.getInstance();


    public AppManager() {
        this.connectionManager = new ConnectionManager(this);
        this.chatManager = new ChatManager(this);
    }

    public void init() {
        this.connectionManager.createUI();
        this.connectionManager.open();
    }

    public void initChat(String peerIp) {
        this.connectionManager.dispose();
        this.chatManager.create(peerIp);
        this.chatManager.open();
    }

    public void initConnection(String peerIp, int peerPort, int userPort, String userName) {
        try {
            Client client = new Client();
            client.initiateClientSocket(peerIp, peerPort, this.chatManager);
            System.out.println("\n###### Client's Initialized");
            this.initChat(peerIp);
        } catch (NoServerException e) {
            ClientServer clientServer = new ClientServer();
            clientServer.initiateClientServerSocket(userPort, this.chatManager);
            System.out.println("\n###### Server initialized ######");
            this.initChat(null);
        } catch (Exception e) {
            this.connectionManager.handleConnectionError(languagesController.getWord("BROKEN"));
        }
    }

}
