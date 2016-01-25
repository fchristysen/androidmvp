package org.sadalsuud.mvpplayground.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.sadalsuud.basemvp.AppUtil;
import org.sadalsuud.basemvp.presenter.BasePresenter;
import org.sadalsuud.basemvp.presenter.PresenterManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements MvpView, PresenterFactory{
    private String TAG;
    private PresenterManager<P> mPresenterManager= new PresenterManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.TAG = this.getClass().getSimpleName();
        AppUtil.log(TAG + " : " + "onCreate");
        mPresenterManager.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppUtil.log(TAG + " : " + "onResume");
        mPresenterManager.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        AppUtil.log(TAG + " : " + "onPause");
        mPresenterManager.onPause(isFinishing());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
        mPresenterManager.onSaveInstanceState(outState);
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    @Override
    public abstract P createPresenter();

}
