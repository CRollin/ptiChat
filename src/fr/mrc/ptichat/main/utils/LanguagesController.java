package fr.mrc.ptichat.main.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Locale;

public class LanguagesController {

    private Map supportedLanguages;
    private ResourceBundle translation;


    public LanguagesController(String language){
        this.supportedLanguages = new HashMap<String, Locale>();
        this.supportedLanguages.put("French", Locale.FRENCH);
        this.supportedLanguages.put("English", Locale.ENGLISH);
        this.translation = ResourceBundle.getBundle("language", (Locale)this.supportedLanguages.get(language));
    }


    public String getWord(String keyword) {
        return this.translation.getString(keyword);
    }
}
