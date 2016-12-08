package main.java.fr.mrc.ptichat.exceptions;

import main.java.fr.mrc.ptichat.utils.LanguagesController;

public class NoServerException extends Exception {

    private LanguagesController languagesController = LanguagesController.getInstance();

    public NoServerException() {
        System.out.println(languagesController.getWord("NO_SERVER_EXCEPTION"));
    }

}
