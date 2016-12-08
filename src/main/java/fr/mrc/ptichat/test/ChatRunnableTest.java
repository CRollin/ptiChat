package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.connection.ChatRunnable;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.net.Socket;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChatRunnableTest {

    private ChatRunnable chatThread;

//    @Before
//    public void setUp() throws Exception {
//        Socket socket = mock(Socket.class);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("hello".getBytes());
//        when(socket.getInputStream()).thenReturn(byteArrayInputStream);
//        when(socket.getOutputStream()).thenReturn(byteArrayOutputStream);
//        chatThread = new ChatRunnable(socket);
//    }
//    @Test
//    public void testInputInThread(){
//        chatThread.run();
//        assertTrue("Buffer Reader not ready", chatThread.getRequestThread().isAlive());
//        assertTrue("Print Writer not ready", chatThread.getResponseThread().isAlive());
//    }
}
