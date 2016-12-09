package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileChooserUI extends JPanel implements ActionListener {
    private JFileChooser chooser;
    public ChatManager chatManager;

    public FileChooserUI(JButton button) {
        button.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        MessageSignatureController msc = new MessageSignatureController();
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("");
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.chatManager.setMessage(msc.getFileTerminationSignature() + " " + chooser.getSelectedFile());
        }
    }

}
