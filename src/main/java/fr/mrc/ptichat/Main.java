package main.java.fr.mrc.ptichat;

import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.connection.Input;
import main.java.fr.mrc.ptichat.ui.ConnexionUI;


/**
 * Created by roxane on 11/10/2016.
 */
public class Main {

    static void UIProgramme(){
        ConnexionUI cui = new ConnexionUI();
        cui.open();
    }

    static void connectionProgramme(){
        // TODO: Replace this method by linking the classes ClientServer and Client to the UI (JFrame)
        Input input = new Input();
        // Initiate the Client Server
        System.out.println("################### Welcome to ptit ChatRunnable ###################");
        ClientServer clientServer = new ClientServer();
        clientServer.initiateClientServerSocket();
        // Initiate the Client to connect to a host
        System.out.println("\nDo you want to connect to a host (y/n) ?");
        String answer = input.getInput();
        if (answer.charAt(0) == 'y'){
            Client client = new Client();
            client.initiateClientSocket();
        } else {
            System.out.println("\n###### Waiting for a connection ######");
        }
    }

    public static void main(String[] args) {
        connectionProgramme();
    }

}