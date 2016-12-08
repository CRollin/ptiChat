package main.java.fr.mrc.ptichat.processing;

import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import java.io.FileOutputStream;
import java.io.IOException;

public class RequestHandler {
    private MessageSignatureController msc;

    public RequestHandler() {
        this.msc = new MessageSignatureController();
    }

    /**
     * Checks if a String is a termination message.
     * @param input the <code>String</code> to check
     * @return <code>true</code> if the String is a termination message, false otherwise
     */
    public boolean isTerminationMessage(String input) {
        return msc.getTerminationSignature().equals(input);
    }

    /**
     * Saves a <code>byte[]</code> into a file at the specified destination.
     * @throws IllegalArgumentException if the input is not an encoded file transmission
     * @param fileName the destination of the file
     * @param bytes the <code>byte[]</code> to write
     * @return <code>true</code> if the write was completed, <code>false</code> otherwise
     */
    public boolean byteArrayToFile(String fileName, byte[] bytes) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            fos.write(bytes, 0, bytes.length - 1);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}