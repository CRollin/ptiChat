package fr.mrc.ptichat.main;

import fr.mrc.ptichat.connection.Client;
import fr.mrc.ptichat.connection.ClientServer;
import fr.mrc.ptichat.connection.Input;

import java.util.Scanner;

/**
 * Created by roxane on 11/10/2016.
 */
public class Main {

    /**
     * Creates the chat in the console
     * @param args
     */
    public static void main(String[] args) {
        // TODO: Replace this method by linking the classes ClientServer and Client to the UI (JFrame)
        Input input = new Input();
        // Initiate the Client Server
        System.out.println("################### Welcome to ptit ChatRunnable ###################");
        ClientServer.initiateClientServerSocket();
        // Initiate the Client to connect to a host
        System.out.println("\nDo you want to connect to a host (y/n) ?");
        String answer = input.getInput();
        if (answer.charAt(0) == 'y'){
            Client.initiateClientSocket();
        } else {
            System.out.println("\n###### Waiting for a connection ######");
        }
    }

    public boolean testExample() {
        return true;
    }

}
