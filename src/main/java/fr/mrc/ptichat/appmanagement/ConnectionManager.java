package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.ui.ConnectionUI;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

import java.util.Hashtable;

import static java.lang.Integer.parseInt;

public class ConnectionManager {
    private AppManager am;
    private ConnectionUI cui;
    private LanguagesController languagesController = LanguagesController.getInstance();
    private String savedFilesDirectory = "";

    public ConnectionManager(AppManager am) {
        this.am = am;
    }

    public void createUI() {
        this.cui = new ConnectionUI(this);
    }

    public void open() {
        this.cui.open();
    }

    public void dispose() {
        this.cui.dispose();
    }

    public void initConnection(Hashtable<String, String> connectionData) {
        try {
            String peerIp = this.getPeerIp(connectionData);
            int peerPort = this.getPeerPort(connectionData);
            int userPort = this.getUserPort(connectionData);
            String userName = this.getUserName(connectionData);
            this.am.initConnection(peerIp, peerPort, userPort, userName);
        } catch (Exception e) {
            this.handleConnectionError(languagesController.getWord("FIELD_ERROR"));
        }


    }

    public void handleConnectionError(String m){
        this.cui.addErrorMessage(m);
    }

    private String getPeerIp(Hashtable<String, String> connectionData) {
        return connectionData.get("PEER_IP");
    }

    private int getPeerPort(Hashtable<String, String> connectionData) {
        return parseInt(connectionData.get("PEER_PORT"));
    }

    private String getUserName(Hashtable<String, String> connectionData){
        return connectionData.get("USER_NAME");
    }

    private int getUserPort(Hashtable<String, String> connectionData) {
        return parseInt(connectionData.get("USER_PORT"));
    }


    public String getSavedFilesDirectory() {
        return savedFilesDirectory;
    }

    public void setSavedFilesDirectory(String savedFilesDirectory) {
        this.savedFilesDirectory = savedFilesDirectory;
    }

}
