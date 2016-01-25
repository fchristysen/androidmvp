package org.sadalsuud.mvpplayground.p01;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.sadalsuud.basemvp.presenter.BasePresenter;

/**
 * Created by fchristysen on 1/24/16.
 */
public class Prs_CountView extends BasePresenter<CV_CountView> {
    private static final String KEY_COUNTER = "counter";

    private int mCounter;

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
        if(presenterState!=null && presenterState.containsKey(KEY_COUNTER)) {
            mCounter = presenterState.getInt(KEY_COUNTER);
        }else{
            mCounter = 0;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
        outPresenterState.putInt(KEY_COUNTER, mCounter);
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        updateView();
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void add(){
        mCounter++;
        updateView();
    }

    public void sub(){
        mCounter--;
        updateView();
    }

    public void nextPage(){
        getView().goToNextPage();
    }

    public void updateView(){
        if(getView()!=null){
            getView().setCounter(mCounter);
        }
    }
}
