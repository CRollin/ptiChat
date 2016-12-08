package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;
import main.java.fr.mrc.ptichat.ui.ConnectionUI;

import java.util.Hashtable;

import static java.lang.Integer.parseInt;

public class ConnectionManager {
    private AppManager am;
    private ConnectionUI cui;

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
            this.handleConnectionError("L'un des champs est mal rempli");
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


}
