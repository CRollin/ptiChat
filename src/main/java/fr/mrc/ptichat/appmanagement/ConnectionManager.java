package main.java.fr.mrc.ptichat.appmanagement;

import java.util.Hashtable;

public class ConnectionManager {
    private static ConnectionManager connectionManager = null;

    private ConnectionManager() {
    }

    public static ConnectionManager geConnectionManagerInstance() {
        if (connectionManager == null) {
            connectionManager = new ConnectionManager();
        }
        return connectionManager;
    }

    public void initConnection(Hashtable<String, String> connectionData) {

    }


}
