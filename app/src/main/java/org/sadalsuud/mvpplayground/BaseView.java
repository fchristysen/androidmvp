package org.sadalsuud.mvpplayground;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import org.sadalsuud.mvpplayground.framework.presenter.MvpPresenter;
import org.sadalsuud.mvpplayground.framework.presenter.PresenterLifecycleManager;
import org.sadalsuud.mvpplayground.framework.presenter.factory.PresenterFactory;
import org.sadalsuud.mvpplayground.framework.view.MvpView;

/**
 * Created by fchristysen on 1/22/16.
 */
public abstract class BaseView extends LinearLayout implements MvpView, PresenterFactory {
    private PresenterLifecycleManager mPresenterLifecycleManager = new PresenterLifecycleManager(this);

    public BaseView(Context context){
        super(context);
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(state);
        mPresenterLifecycleManager.onRestoreInstanceState((Bundle)state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putBundle(PRESENTER_STATE_KEY, mPresenterLifecycleManager.onSaveInstanceState());
        bundle.putParcelable(PARENT_STATE_KEY, super.onSaveInstanceState());
        return bundle;
        return super.onSaveInstanceState();
        mPresenterLifecycleManager.onRestoreInstanceState((Bundle)state);
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
    public abstract MvpPresenter createPresenter();

    @Override
    public MvpPresenter getPresenter() {
        return mPresenterLifecycleManager.getPresenter();
    }
}
