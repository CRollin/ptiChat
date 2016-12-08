package main.java.fr.mrc.ptichat.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class propertiesController {

    Properties ps;

    public propertiesController(String propFileName) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(propFileName);
        ps = new Properties();
        try {
            ps.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getStringValue(String key){
        return ps.getProperty(key);
    }

    public int getIntValue(String key) {

        return Integer.parseInt(ps.getProperty(key));
    }

    public boolean getBooleanValue(String key) {
        return ps.getProperty(key).equals("true");
    }

    public String[] getArrayValue(String key) {
        return ps.getProperty(key).split(",");
    }
}
