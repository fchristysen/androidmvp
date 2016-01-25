package org.sadalsuud.mvpplayground.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sadalsuud.basemvp.presenter.BasePresenter;
import org.sadalsuud.basemvp.presenter.PresenterManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView, PresenterFactory{
    private PresenterManager<P> mPresenterManager= new PresenterManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenterManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenterManager.onPause(isFinishing());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterManager.onSaveInstanceState(outState);
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    @Override
    public abstract P createPresenter();

}
