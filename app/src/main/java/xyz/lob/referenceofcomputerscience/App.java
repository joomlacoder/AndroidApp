package xyz.lob.referenceofcomputerscience;


import android.app.Application;
import android.content.SharedPreferences;

import xyz.lob.referenceofcomputerscience.content.Content;

public class App extends Application {

    public static final String APP_PREFERENCES = "myForever";
    public static final String APP_PREFERENCES_FOREVER = "forever";

    private SharedPreferences preferences;

    private Content content;
    private static App instance;

    public static  App getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        content = new Content(this);
    }

    public Content getContent() {
        return content;
    }

    public static SharedPreferences getPreferences(){
        return instance.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
    }
}
