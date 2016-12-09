package main.java.fr.mrc.ptichat.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractChooserUI extends JPanel implements ActionListener {
    protected JFileChooser chooser;
    protected boolean filesOnly;
    protected String title = "";
    private String lastPath = ".";

    public AbstractChooserUI(JButton button, boolean filesOnly) {
        button.addActionListener(this);
        this.filesOnly = filesOnly;
    }

    protected abstract void performSideEffectAction(String selection);

    public void actionPerformed(ActionEvent e) {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File(this.lastPath));
        chooser.setDialogTitle(this.title);
        chooser.setFileSelectionMode(this.filesOnly ? JFileChooser.FILES_ONLY : JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.lastPath = chooser.getSelectedFile().toString();
            this.performSideEffectAction(this.lastPath);
        }
    }

}

