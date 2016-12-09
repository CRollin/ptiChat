package main.java.fr.mrc.ptichat;

import main.java.fr.mrc.ptichat.appmanagement.AppManager;

import main.java.fr.mrc.ptichat.utils.LanguagesController;

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
