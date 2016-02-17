package org.sadalsuud.mvpplayground.p03;

import android.os.Bundle;

import org.sadalsuud.mvpplayground.base.BasePresenter;

/**
 * Created by fchristysen on 1/27/16.
 */
public class Prs_CustomViewList extends BasePresenter<Act_CustomViewList> {

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
