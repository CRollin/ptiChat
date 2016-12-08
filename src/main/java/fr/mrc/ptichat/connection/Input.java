package main.java.fr.mrc.ptichat.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Input {

    public String getInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            while(!br.ready()) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                    // System.out.println("Input interrupted!");
                    return null;
                }
            }
            input = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
    public int getInt(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
