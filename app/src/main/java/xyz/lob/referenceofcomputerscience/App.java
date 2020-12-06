package xyz.lob.referenceofcomputerscience;


import android.app.Application;
import android.util.Log;

import xyz.lob.referenceofcomputerscience.content.Content;

public class App extends Application {

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
}
