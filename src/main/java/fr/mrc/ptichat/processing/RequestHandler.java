package main.java.fr.mrc.ptichat.processing;

import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

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
     * Decodes an array of byte into a message.
     * @throws IllegalArgumentException if the input is not an encoded message
     * @param input the <code>byte[]</code> to decode
     * @return a <code>String</code> representation of the decoded message
     */
    public String getMessage(byte[] input) {
        if (!this.isMessage(input)) throw new IllegalArgumentException();
        return new String(Arrays.copyOfRange(input, 1, input.length));
    }

}