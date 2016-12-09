package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

/**
 * Coordinates the interactions between ui objects
 */
public class AppManager {

    private ConnectionManager connectionManager;
    private ChatManager chatManager;
    private LanguagesController languagesController = LanguagesController.getInstance();


    public AppManager() {
        this.connectionManager = new ConnectionManager(this);
        this.chatManager = new ChatManager(this);
    }

    /**
     * Initializes the connection UI and open it
     */
    public void init() {
        this.connectionManager.createUI();
        this.connectionManager.open();
    }

    /**
     * Closes the connection UI
     * Initializes the chat UI and open it
     */
    public void initChat(String peerIp) {
        this.connectionManager.dispose();
        this.chatManager.create(peerIp);
        this.chatManager.open();
    }

    /*
     * Reopens the connection UI when the user leaves the chat frame
     */
    public void leaveChat() {
        this.connectionManager.open();
    }

    /**
     * Initializes connection between the peers after having completed the connection form.
     * If the peer is not founded, the user wait for someone to connect on his port
     * In this case, the identity of the peer that will connect to him is not checked
     */
    public void initConnection(String peerIp, int peerPort, int userPort, String userName) {
        String dirPath = this.connectionManager.getSavedFilesDirectory();
        if (dirPath.length() > 0) this.chatManager.setSavedFilesDirectory(dirPath);
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
