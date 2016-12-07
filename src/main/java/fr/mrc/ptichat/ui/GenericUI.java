package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.utils.LanguagesController;
import main.java.fr.mrc.ptichat.utils.UIStyleController;

import javax.swing.*;
import java.awt.*;

public abstract class GenericUI extends JFrame {

    protected LanguagesController languagesController = new LanguagesController("French");
    protected UIStyleController uiStyleController = new UIStyleController();
    private String windowType;

    public GenericUI(String windowType) {
        this.windowType = windowType;
        this.setGlobalParameters(this.windowType);
        this.createUI();
        this.pack();
    }

    /**
     * Formats the window.
     */
    private void setGlobalParameters(String windowType){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension d = new Dimension(this.uiStyleController.getIntValue(windowType + ".width"),
                this.uiStyleController.getIntValue(windowType + ".height"));
        this.setMinimumSize(d);
        this.setResizable(this.uiStyleController.getBooleanValue(windowType + ".resizable"));
        this.setLocationRelativeTo(null);
    }

    protected Dimension createDimension(String widthKey, String heightKey) {
        Dimension d =  new Dimension(this.uiStyleController.getIntValue(widthKey),
                this.uiStyleController.getIntValue(heightKey));
        return d;
    }

    public void open(){
        this.setVisible(true);
    }

    abstract protected void createUI();
}
