package main.java.fr.mrc.ptichat.test;


import main.java.fr.mrc.ptichat.processing.RequestHandler;
import main.java.fr.mrc.ptichat.processing.ResponseHandler;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

public class ResponseHandlerTest {

    private ResponseHandler rh = new ResponseHandler();

    @Test
    public void getTerminationBytes() throws Exception {
        assertArrayEquals("Q".getBytes() , rh.getTerminationBytes());
    }

    @Test
    public void formatMessage() throws Exception {
        assertArrayEquals("M".getBytes() , rh.formatMessage(null));
        assertArrayEquals("Mtoto".getBytes() , rh.formatMessage("toto"));
    }

    @Test
    public void fileToByteArray() {
        RequestHandler reqH = new RequestHandler();
        String pathToTesResources = "./src/main/java/fr/mrc/ptichat/test/resources";
        byte[] toto = rh.fileToByteArray(pathToTesResources + "/test.jpg");
        reqH.byteArrayToFile(pathToTesResources + "/test2.jpg", toto);
        File newFile = new File(pathToTesResources + "/test2.jpg");
        assertTrue(newFile.exists() && (System.currentTimeMillis() - newFile.lastModified()) <= 5000L);
    }

}