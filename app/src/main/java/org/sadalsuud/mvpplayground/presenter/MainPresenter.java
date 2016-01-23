package org.sadalsuud.mvpplayground.presenter;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.BasePresenter;
import org.sadalsuud.mvpplayground.view.MainActivity;

/**
 * Created by fchristysen on 1/23/16.
 */
public class MainPresenter extends BasePresenter<MainActivity> {


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
