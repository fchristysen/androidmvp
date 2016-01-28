package org.sadalsuud.mvpplayground.p01;

import android.os.Bundle;
import android.support.annotation.Nullable;

import org.sadalsuud.mvpplayground.base.BasePresenter;

import icepick.State;

/**
 * Created by fchristysen on 1/24/16.
 */
public class Prs_CountView extends BasePresenter<CV_CountView> {
    @State protected int mCounter;

    @Override
    public void onCreate(@Nullable Bundle presenterState) {
        super.onCreate(presenterState);
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
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
