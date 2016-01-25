package org.sadalsuud.mvpplayground;

import android.app.Application;
import android.content.Context;

import org.sadalsuud.mvpplayground.p00.MainModelHandler;

import java.lang.ref.WeakReference;

/**
 * Created by fchristysen on 1/23/16.
 */
public class App extends Application{
    public static WeakReference<Context> CONTEXT;
    private static MainModelHandler sModelHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        App.sModelHandler = new MainModelHandler(getApplicationContext());
        App.CONTEXT = new WeakReference<Context>(getApplicationContext());
    }

    public static MainModelHandler getMainModelHandler() {
        return sModelHandler;
    }
}
