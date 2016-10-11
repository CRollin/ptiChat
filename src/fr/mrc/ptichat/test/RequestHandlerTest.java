package fr.mrc.ptichat.test;

import fr.mrc.ptichat.main.processing.RequestHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHandlerTest {

    private RequestHandler rh = new RequestHandler();
    private final byte[] EMPTY_MSG_INPUT = "M".getBytes();
    private final byte[] MSG_INPUT = "Mtoto".getBytes();
    private final byte[] TERMINATION_INPUT = "Qtoto".getBytes();

    @Test
    public void isMessage() throws Exception {
        assertTrue(rh.isMessage(this.EMPTY_MSG_INPUT));
        assertTrue(rh.isMessage(this.MSG_INPUT));

        assertFalse(rh.isMessage(null));
        assertFalse(rh.isMessage(this.TERMINATION_INPUT));
    }

    @Test
    public void isTerminationBytes() throws Exception {
        assertFalse(rh.isTerminationBytes(this.EMPTY_MSG_INPUT));
        assertFalse(rh.isTerminationBytes(this.MSG_INPUT));

        assertFalse(rh.isTerminationBytes(null));
        assertTrue(rh.isTerminationBytes(this.TERMINATION_INPUT));
    }

    @Test
    public void getMessage() throws Exception {
        assertEquals("", rh.getMessage(this.EMPTY_MSG_INPUT));
        assertEquals("toto", rh.getMessage(this.MSG_INPUT));

        try {
            assertEquals("toto", rh.getMessage(null));
            fail();
        } catch(IllegalArgumentException iae) {
            assertTrue(true);
        }
        try {
            assertEquals("toto", rh.getMessage(this.TERMINATION_INPUT));
            fail();
        } catch(IllegalArgumentException iae) {
            assertTrue(true);
        }
    }

}