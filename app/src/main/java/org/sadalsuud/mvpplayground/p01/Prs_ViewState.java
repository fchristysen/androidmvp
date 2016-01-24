package org.sadalsuud.mvpplayground.p01;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.BasePresenter;

/**
 * Created by fchristysen on 1/23/16.
 */
public class Prs_ViewState extends BasePresenter<Act_ViewState> {

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

}
