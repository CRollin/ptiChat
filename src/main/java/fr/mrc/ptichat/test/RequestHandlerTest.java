package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.processing.RequestHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHandlerTest {

    private RequestHandler rh = new RequestHandler();
    private final byte[] EMPTY_MSG_INPUT = "M".getBytes();
    private final byte[] MSG_INPUT = "Mtoto".getBytes();
    private final byte[] TERMINATION_INPUT = "Qtoto".getBytes();

    @Test
    public void isMessage() throws Exception {
        assertFalse(rh.isMessage(null));
        assertTrue(rh.isMessage(this.EMPTY_MSG_INPUT));
        assertTrue(rh.isMessage(this.MSG_INPUT));
        assertFalse(rh.isMessage(this.TERMINATION_INPUT));
    }

    @Test
    public void isTerminationBytes() throws Exception {
        assertFalse(rh.isTerminationBytes(null));
        assertFalse(rh.isTerminationBytes(this.EMPTY_MSG_INPUT));
        assertFalse(rh.isTerminationBytes(this.MSG_INPUT));
        assertTrue(rh.isTerminationBytes(this.TERMINATION_INPUT));
    }

    @Test
    public void isFileTransmission() {
        assertFalse(rh.isFileTransmission(null));
        assertTrue(rh.isFileTransmission("F".getBytes()));
        assertTrue(rh.isFileTransmission("Fuck you ;)".getBytes()));
    }

    @Test
    public void getMessage() throws Exception {
        try { // null should throw IllegalArgumentException
            assertEquals("toto", rh.getMessage(null));
            fail();
        } catch(IllegalArgumentException iae) {
            assertTrue(true);
        }
        assertEquals("", rh.getMessage(this.EMPTY_MSG_INPUT));
        assertEquals("toto", rh.getMessage(this.MSG_INPUT));
        try { // wrong format should throw IllegalArgumentException
            assertEquals("toto", rh.getMessage(this.TERMINATION_INPUT));
            fail();
        } catch(IllegalArgumentException iae) {
            assertTrue(true);
        }
    }

}