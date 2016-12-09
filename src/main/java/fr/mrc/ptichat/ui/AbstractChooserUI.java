package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractChooserUI extends JPanel implements ActionListener {
    protected JFileChooser chooser;
    protected boolean filesOnly;

    public AbstractChooserUI(JButton button, boolean filesOnly) {
        button.addActionListener(this);
        this.filesOnly = filesOnly;
    }

    protected abstract void performSideEffectAction(String selection);

    public void actionPerformed(ActionEvent e) {
        MessageSignatureController msc = new MessageSignatureController();
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(this.filesOnly ? JFileChooser.FILES_ONLY : JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.performSideEffectAction(chooser.getSelectedFile().toString());
        }
    }

}

