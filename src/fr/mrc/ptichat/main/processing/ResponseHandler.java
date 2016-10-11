package fr.mrc.ptichat.main.processing;

public class ResponseHandler {

    public ResponseHandler() {
    }

    /**
     * Returns the termination signal for the Peer to Peer chat.
     * @return the <code>byte[]</code> representation of the termination signal
     */
    public byte[] getTerminationBytes() {
        return "Q".getBytes();
    }

    /**
     * Formats a string for the Peer to Peer chat.
     * @param msg the <code>String</code> to format
     * @return the representation of <code>msg</code>
     */
    public byte[] formatMessage(String msg) {
        return ("M" + ((msg == null) ? "" : msg)).getBytes();
    }

}
