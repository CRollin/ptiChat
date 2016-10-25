package fr.mrc.ptichat.main.processing;

import fr.mrc.ptichat.main.utils.MessageSignatureController;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class RequestHandler {
    private MessageSignatureController msc;

    public RequestHandler() {
        this.msc = new MessageSignatureController();
    }

    private boolean checkFirstByte(byte[] input, char expectedChar) {
        return (input != null) && (input.length > 0) && (char) (input[0] & 0xFF) == expectedChar;
    }

    /**
     * Checks if an array of bytes encodes a message.
     * @param input the <code>byte[]</code> to check
     * @return <code>true</code> if the array represents a message, false otherwise
     */
    public boolean isMessage(byte[] input) {
        return this.checkFirstByte(input, msc.getMessageSignature());
    }

    /**
     * Checks if an array of bytes encodes a termination signal.
     * @param input the <code>byte[]</code> to check
     * @return <code>true</code> if the array represents a termination signal, false otherwise
     */
    public boolean isTerminationBytes(byte[] input) {
        return this.checkFirstByte(input, msc.getTerminationSignature());
    }

    /**
     * Checks if an array of bytes encodes a file transmission.
     * @param input the <code>byte[]</code> to check
     * @return <code>true</code> if the array represents a file transmission, <code>false</code> otherwise
     */
    public boolean isFileTransmission(byte[] input) {
        return this.checkFirstByte(input, 'F');
    }

    /**
     * Decodes an array of byte into a message.
     * @throws IllegalArgumentException if the input is not an encoded message
     * @param input the <code>byte[]</code> to decode
     * @return a <code>String</code> representation of the decoded message
     */
    public String getMessage(byte[] input) {
        if (!this.isMessage(input)) throw new IllegalArgumentException();
        return new String(Arrays.copyOfRange(input, 1, input.length));
    }

    /**
     * Saves a <code>byte[]</code> into a file at the specified destination.
     * @throws IllegalArgumentException if the input is not an encoded file transmission
     * @param fileName the destination of the file
     * @param bytes the <code>byte[]</code> to write
     * @return <code>true</code> if the write was completed, <code>false</code> otherwise
     */
    public boolean byteArrayToFile(String fileName, byte[] bytes) {
        if (isFileTransmission(bytes)) {
            try {
                FileOutputStream fos = new FileOutputStream(fileName);
                fos.write(bytes, 1, bytes.length - 1);
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else throw new IllegalArgumentException();
    }

}