package org.sadalsuud.mvpplayground;

import android.app.Application;

import org.sadalsuud.mvpplayground.model.MainModelHandler;

/**
 * Created by fchristysen on 1/23/16.
 */
public class App extends Application{
    private static MainModelHandler sModelHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        App.sModelHandler = new MainModelHandler(getApplicationContext());
    }

    public static MainModelHandler getMainModelHandler() {
        return sModelHandler;
    }
}
