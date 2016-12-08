package main.java.fr.mrc.ptichat.processing;

import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import java.io.*;

public class ResponseHandler {
    private MessageSignatureController msc;

    public ResponseHandler() {
        this.msc = new MessageSignatureController();
    }

    /**
     * Returns the termination signal for the Peer to Peer chat.
     * @return the <code>byte[]</code> representation of the termination signal
     */
    public String getTerminationMessage() {
        return msc.getTerminationSignature();
    }

    /**
     * Encodes a file into an array of bytes.
     * @param filename the location of the file
     * @return the <code>byte[]</code> representation of the file
     */
    public byte[] fileToByteArray(String filename) {
        File fileToSend = new File(filename);
        int n;
        byte[] result = new byte[(int) fileToSend.length()];
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileToSend))) {
            n = bis.read(result, 0, result.length - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}