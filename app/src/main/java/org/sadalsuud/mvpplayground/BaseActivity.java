package org.sadalsuud.mvpplayground;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.sadalsuud.mvpplayground.framework.presenter.PresenterLifecycleManager;
import org.sadalsuud.mvpplayground.framework.presenter.MvpPresenter;
import org.sadalsuud.mvpplayground.framework.presenter.factory.PresenterFactory;
import org.sadalsuud.mvpplayground.framework.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements MvpView, PresenterFactory{
    private PresenterLifecycleManager mPresenterLifecycleManager= new PresenterLifecycleManager(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
    public abstract MvpPresenter createPresenter();

    @Override
    public MvpPresenter getPresenter() {
        return mPresenterLifecycleManager.getPresenter();
    }
}
