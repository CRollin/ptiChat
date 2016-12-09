package main.java.fr.mrc.ptichat.ui;

import main.java.fr.mrc.ptichat.appmanagement.ConnectionManager;
import main.java.fr.mrc.ptichat.utils.LanguagesController;

import javax.swing.*;
import java.io.File;

public class DirectoryChooserUI extends AbstractChooserUI {
    public ConnectionManager cm;

    public DirectoryChooserUI(JButton button) {
        super(button, false);
        this.title = LanguagesController.getInstance().getWord("SET_DIR_PATH_TITLE");
    }

    @Override
    protected void performSideEffectAction(String selection) {
        this.cm.setSavedFilesDirectory(selection + File.separator.replace("\\", "\\\\"));
    }
}
