package org.sadalsuud.mvpplayground.p02;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.BasePresenter;

/**
 * Created by fchristysen on 1/27/16.
 */
public class Prs_NoId extends BasePresenter<Act_NoId> {

    //region lifecycle
    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
    }
    //endregion

    public void textChanged(String text){

    }

    public void checkChanged(boolean isChecked){

    }
}
