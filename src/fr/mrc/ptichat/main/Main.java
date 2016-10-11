package fr.mrc.ptichat.main;

import fr.mrc.ptichat.connection.Client;
import fr.mrc.ptichat.connection.ClientServer;
import java.util.Scanner;

/**
 * Created by roxane on 11/10/2016.
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("################### Welcome to ptit Chat ###################");
        ClientServer.initiateClientServerSocket();
        System.out.println("\nDo you want to connect to a host (y/n) ?");
        String answer = sc.nextLine();
        if (answer.charAt(0) == 'y'){
            Client.initiateClientSocket();
        } else {
            System.out.println("\n###### Waiting for a connection ######");
        }

    }

}
