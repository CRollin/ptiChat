package fr.mrc.ptichat.main;

import fr.mrc.ptichat.connexion.Client;
import fr.mrc.ptichat.connexion.ClientServer;
/**
 * Created by roxane on 11/10/2016.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("################### Welcome to ptit Chat ###################");
        ClientServer.initiateClientServerSocket();
        Client.initiateClientSocket();
    }

    public boolean testExample() {
        return true;
    }

}
