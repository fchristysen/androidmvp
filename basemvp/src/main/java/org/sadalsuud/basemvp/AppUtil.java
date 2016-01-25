package org.sadalsuud.basemvp;

import android.util.Log;

/**
 * Created by fchristysen on 1/25/16.
 */
public class AppUtil {

    public static final boolean isVerbose = true;
    public static void log(String message){
        if(isVerbose){
//            if(App.CONTEXT.get()!=null) {
//                Toast.makeText(App.CONTEXT.get(), message, Toast.LENGTH_SHORT).show();
//            }
            Log.i("AppUtil", message);
        }
    }

}
