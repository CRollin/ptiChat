package fr.mrc.ptichat.test;

import fr.mrc.ptichat.main.Main;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
        Assert.assertTrue(main.testExample());
    }

}