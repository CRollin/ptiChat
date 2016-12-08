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
            String hostIp = this.getHostIp(connectionData);
            int hostPort = this.getHostPort(connectionData);
            int serverPort = this.getServerPort(connectionData);
            this.am.initConnection(hostIp, hostPort, serverPort);
        } catch (Exception e) {
            this.handleConnectionError("L'un des champs est mal rempli");
        }


    }

    public void handleConnectionError(String m){
        this.cui.addErrorMessage(m);
    }

    private String getHostIp(Hashtable<String, String> connectionData) {
        return connectionData.get("PEER_IP");
    }

    private int getHostPort(Hashtable<String, String> connectionData) {
        return parseInt(connectionData.get("PEER_PORT"));
    }

    private int getServerPort(Hashtable<String, String> connectionData) {
        return parseInt(connectionData.get("USER_PORT"));
    }


}
