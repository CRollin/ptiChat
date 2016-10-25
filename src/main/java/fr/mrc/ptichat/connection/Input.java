package main.java.fr.mrc.ptichat.connection;

import java.util.Scanner;

public class Input {

    public String getInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
    public int getInt(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
