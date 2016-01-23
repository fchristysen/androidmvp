package org.sadalsuud.mvpplayground.model;

import android.content.Context;

import org.sadalsuud.mvpplayground.view.MainActivity;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by fchristysen on 1/23/16.
 */
public class MainModelHandler {
    private static final int WAIT_TIME_LOWEST = 2000;      //millis
    private static final int WAIT_TIME_HIGHEST = 5000;      //millis
    public static final Class[] PAGES = new Class[]{
            MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
            , MainActivity.class
    };

    public MainModelHandler(Context context){

    }

    public Observable<List<Class>> getPageList(){
        List<Class> list = Arrays.asList(PAGES);
        return Observable.just(list).delay(getWaitTime(), TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private int getWaitTime(){
        int waitTime = new Random().nextInt(WAIT_TIME_HIGHEST-WAIT_TIME_LOWEST);
        return waitTime + WAIT_TIME_LOWEST;
    }
}
