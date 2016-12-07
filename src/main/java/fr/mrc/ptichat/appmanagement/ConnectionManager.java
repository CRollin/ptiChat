package main.java.fr.mrc.ptichat.appmanagement;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;

import java.util.Hashtable;

import static java.lang.Integer.parseInt;

public class ConnectionManager {
    private AppManager am;

    public ConnectionManager(AppManager am) {
        this.am = am;
    }

    public void initConnection(Hashtable<String, String> connectionData) {
        try {
            int hostPort = this.getHostPort(connectionData);
            String hostIp = this.getHostIp(connectionData);
            Client client = new Client();
            client.initiateClientSocket(hostIp, hostPort);
            this.am.initChat("\n###### Client's Initialized");
        } catch (NoServerException e) {
            this.am.initChat("\n###### Client's Initialisation failed ######");
            int serverPort = this.getServerPort(connectionData);
            ClientServer clientServer = new ClientServer();
            clientServer.initiateClientServerSocket(serverPort);
            this.am.initChat("\n###### Server initialized ######");
        } catch (Exception e) {
            this.am.handleConnectionError("c tou kassé");
        }


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
