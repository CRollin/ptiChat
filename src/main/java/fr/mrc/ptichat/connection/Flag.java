package main.java.fr.mrc.ptichat.connection;

import java.util.concurrent.atomic.AtomicBoolean;

public class Flag {
    private AtomicBoolean flag = new AtomicBoolean(false);

    public boolean getFlag() {return this.flag.get();}

    public void setFlag(boolean value) {this.flag.set(value);}

}
