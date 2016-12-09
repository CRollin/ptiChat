package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.appmanagement.ChatManager;
import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import javax.swing.JButton;

public class FileChooserUI extends AbstractChooserUI {
    private MessageSignatureController msc = new MessageSignatureController();
    public ChatManager chatManager;

    public FileChooserUI(JButton button, boolean filesOnly) {
        super(button, filesOnly);
    }

    @Override
    protected void performSideEffectAction(String selection) {
        this.chatManager.setMessage(this.msc.getFileTerminationSignature() + " " + this.chooser.getSelectedFile());
    }

}
