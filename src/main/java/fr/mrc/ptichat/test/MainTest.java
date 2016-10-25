package main.java.fr.mrc.ptichat.test;

import main.java.fr.mrc.ptichat.Main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by roxane on 11/10/2016.
 */
public class MainTest {
    Main main;

    @Before
    public void init(){
        System.out.println("Setting up ...");
        this.main = new Main();
    }
    
    @Test
    public void testExample() throws Exception {
        Assert.assertNotNull(main);
    }

}