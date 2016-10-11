package fr.mrc.ptichat.connection;

import java.io.PrintWriter;
import java.util.Scanner;

public class Response implements Runnable {

    private PrintWriter out = null;
    private String message = null;
    private Scanner sc;

    public Response(PrintWriter out){
        this.out = out;
    }

    public void run(){
        sc = new Scanner(System.in);
        while(true) {
            System.out.println("Type a message : ");
            message = sc.nextLine();
            out.println(message);
            out.flush();
        }
    }
}
