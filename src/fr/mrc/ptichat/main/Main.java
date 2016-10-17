package fr.mrc.ptichat.main;

import fr.mrc.ptichat.main.ui.ConnexionUI;

public class Main {

    public boolean testExample() {
        return true;
    }

    public static void main(String[] args) {
        ConnexionUI cui = new ConnexionUI();
        cui.open();
    }
}
