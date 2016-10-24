package fr.mrc.ptichat.main.utils;

import java.io.IOException;
import java.util.Properties;

public class MessageSignatureController {
    Properties ps;

    public MessageSignatureController() {
        ps = new Properties();
        try {
            ps.load(new java.io.FileInputStream("./src/fr/mrc/ptichat/resources/messageSignature.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char getRequestSignature() {
        return ps.getProperty("REQUEST").charAt(0);
    }
    public char getAcknowledgeSignature() {
        return ps.getProperty("ACKNOWLEDGE").charAt(0);
    }
    public char getMessageSignature() {
        return ps.getProperty("MESSAGE").charAt(0);
    }
    public char getTerminationSignature() {
        return ps.getProperty("QUIT").charAt(0);
    }

}
