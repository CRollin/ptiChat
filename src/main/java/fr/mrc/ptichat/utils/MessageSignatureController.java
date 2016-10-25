package main.java.fr.mrc.ptichat.utils;

public class MessageSignatureController extends propertiesController {

    static String propFileName = "messageSignature.properties";

    public MessageSignatureController() {
        super(propFileName);
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
