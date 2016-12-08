package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.processing.RequestHandler;
import org.junit.Test;

import static org.junit.Assert.*;

public class RequestHandlerTest {

    private RequestHandler rh = new RequestHandler();
    private final String EMPTY_MSG_STRING = "";
    private final String MSG_STRING = "Coucou";
    private final String TERMINATION_STRING = "/exit";

    @Test
    public void isTerminationMessage() throws Exception {
        assertFalse(rh.isTerminationMessage(null));
        assertFalse(rh.isTerminationMessage(this.EMPTY_MSG_STRING));
        assertFalse(rh.isTerminationMessage(this.MSG_STRING));
        assertTrue(rh.isTerminationMessage(this.TERMINATION_STRING));
    }

}