package xyz.lob.referenceofcomputerscience;


import android.app.Application;
import android.content.SharedPreferences;
import android.util.ArraySet;

import java.util.LinkedHashSet;
import java.util.Set;

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
        preferences = instance.getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE);
        content = new Content(this);
    }

    public Set<String> getForevers(){
        return preferences.getStringSet(App.APP_PREFERENCES_FOREVER, new ArraySet<>());
    }

    public void addForever(String title){
        Set<String> stringForever = new LinkedHashSet<>(preferences.getStringSet(App.APP_PREFERENCES_FOREVER, new ArraySet<>()));
        stringForever.add(title);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(App.APP_PREFERENCES_FOREVER, stringForever);
        editor.apply();
    }

    public void removeForever(String title){
        Set<String> stringForever = new LinkedHashSet<>(preferences.getStringSet(App.APP_PREFERENCES_FOREVER, new ArraySet<>()));
        stringForever.remove(title);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet(App.APP_PREFERENCES_FOREVER, stringForever);
        editor.apply();
    }

    public Content getContent() {
        return content;
    }

    public SharedPreferences getPreferences(){
        return preferences;
    }
}
