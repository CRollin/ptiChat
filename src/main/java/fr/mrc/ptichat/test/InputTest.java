package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.connection.Input;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

public class InputTest {

    private Input input = new Input();

    @Test
    public void testGetInput(){
        String message = "add 5";
        InputStream in = new ByteArrayInputStream(message.getBytes());
        System.setIn(in);
        assertEquals("add 5", input.getInput());
    }
    @Test
    public void testGetInt(){
        String message = "8080";
        InputStream in = new ByteArrayInputStream(message.getBytes());
        System.setIn(in);
        assertEquals(8080, input.getInt());
    }
}
