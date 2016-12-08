package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.connection.Flag;
import main.java.fr.mrc.ptichat.connection.RequestRunnable;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.startsWith;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.net.InetAddress;

public class RequestRunnableTest {

    private BufferedReader in = mock(BufferedReader.class);
    private PrintStream out = mock(PrintStream.class);
    private RequestRunnable request;
    private Thread t;

    @Before
    public void setUp() throws Exception {
        System.setOut(out);
        when(in.readLine()).thenReturn("line1", "");
        request = new RequestRunnable(in, InetAddress.getByName("127.0.0.1"), 8080, new Flag());
        t = new Thread(request);
    }

    @After
    public void tearDown() throws Exception {
        System.setOut(null);
        request.stop();
    }

    @Test
    public void testInputInThread(){
        t.start();
        try {
            verify(out).println(startsWith("Received (me: /127.0.0.1:8080) : line1"));
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
