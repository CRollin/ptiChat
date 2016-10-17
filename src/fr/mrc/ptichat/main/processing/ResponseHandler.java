package fr.mrc.ptichat.main.processing;

import fr.mrc.ptichat.main.utils.MessageSignatureController;

public class ResponseHandler {
    private MessageSignatureController msc;

    public ResponseHandler() {
        this.msc = new MessageSignatureController();
    }

    /**
     * Returns the termination signal for the Peer to Peer chat.
     * @return the <code>byte[]</code> representation of the termination signal
     */
    public byte[] getTerminationBytes() {
        return String.valueOf(msc.getTerminationSignature()).getBytes();
    }

    /**
     * Formats a string for the Peer to Peer chat.
     * @param msg the <code>String</code> to format
     * @return the representation of <code>msg</code>
     */
    public byte[] formatMessage(String msg) {
        return (msc.getMessageSignature() + ((msg == null) ? "" : msg)).getBytes();
    }

}