package org.sadalsuud.mvpplayground.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sadalsuud.basemvp.presenter.BasePresenter;
import org.sadalsuud.basemvp.presenter.PresenterLifecycleManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView, PresenterFactory{
    private PresenterLifecycleManager<P> mPresenterLifecycleManager= new PresenterLifecycleManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenterLifecycleManager.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenterLifecycleManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenterLifecycleManager.onPause(isFinishing());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mPresenterLifecycleManager.onSaveInstanceState(outState);
    }

    @Override
    public final P getPresenter() {
        return mPresenterLifecycleManager.getPresenter();
    }

    @Override
    public abstract P createPresenter();

}
