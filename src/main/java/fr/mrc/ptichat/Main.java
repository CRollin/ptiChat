package main.java.fr.mrc.ptichat;

import main.java.fr.mrc.ptichat.appmanagement.AppManager;
import main.java.fr.mrc.ptichat.connection.Client;
import main.java.fr.mrc.ptichat.connection.ClientServer;
import main.java.fr.mrc.ptichat.connection.Input;

import main.java.fr.mrc.ptichat.ui.ConnectionUI;
import main.java.fr.mrc.ptichat.exceptions.NoServerException;
import main.java.fr.mrc.ptichat.utils.LanguagesController;


/**
 * Created by roxane on 11/10/2016.
 */
public class Main {

    static void UIProgramme(){
        LanguagesController languagesController = LanguagesController.getInstance();
        languagesController.setLanguage("English");
        AppManager am = new AppManager();
        am.init();
    }

    public static void main(String[] args) {
        UIProgramme();
    }

}
