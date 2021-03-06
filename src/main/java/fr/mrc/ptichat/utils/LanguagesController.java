package main.java.fr.mrc.ptichat.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Locale;

public class LanguagesController {

    private Map supportedLanguages;
    private ResourceBundle translation;


    private LanguagesController(){
        this.supportedLanguages = new HashMap<String, Locale>();
        this.supportedLanguages.put("French", Locale.FRENCH);
        this.supportedLanguages.put("English", Locale.ENGLISH);
        this.translation = ResourceBundle.getBundle("language", (Locale)this.supportedLanguages.get("English"));
    }

    private static LanguagesController INSTANCE = null;

    public static LanguagesController getInstance(){
        if (INSTANCE == null){
            INSTANCE = new LanguagesController();
        }
        return INSTANCE;
    }

    public void setLanguage(String language){
        this.translation = ResourceBundle.getBundle("language", (Locale)this.supportedLanguages.get(language));
    }

    public String getWord(String keyword) {
        String value = this.translation.getString(keyword);
        try {
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return value;
        }
    }
}
