package fr.mrc.ptichat.connexion;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ClientServer {

    public static ServerSocket serverSocket = null;
    public static Thread acceptConnexionsThread;

    public static void initiateClientServerSocket(){
        try {
            // Server initialisation
            System.out.println("\n###### Client's server socket Initialisation ######");
            Scanner sc = new Scanner(System.in);
            System.out.println("What port do you want to use for the server?");
            int port = sc.nextInt();
            serverSocket = new ServerSocket(port);
            System.out.println("Server listening to the port "+serverSocket.getLocalPort());
            // Handle the connexion requests
            acceptConnexionsThread = new Thread(new AcceptConnexions(serverSocket));
            acceptConnexionsThread.start();
        } catch (IOException e){
            System.out.println("The port " + serverSocket.getLocalPort() + " is already used.");
        }
    }
}
