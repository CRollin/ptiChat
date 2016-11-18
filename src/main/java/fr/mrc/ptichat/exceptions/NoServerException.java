package main.java.fr.mrc.ptichat.exceptions;

public class NoServerException extends Exception {

    public NoServerException() {
        System.out.println("The server you are trying to connect to is not running.");
    }

}
