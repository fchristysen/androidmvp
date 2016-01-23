package org.sadalsuud.mvpplayground.framework.presenter;

import android.os.Bundle;

import org.sadalsuud.mvpplayground.framework.presenter.factory.PresenterFactory;
import org.sadalsuud.mvpplayground.framework.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 * This class purpose is to receive activity's lifecycle and then manage the presenter
 */
public class PresenterLifecycleManager<P extends MvpPresenter> {
    private static final String KEY_PRESENTER_ID = "presenter_id";
    private static final String KEY_PRESENTER_STATE = "presenter_state";

    private PresenterFactory<P> mPresenterFactory;
    private P mPresenter;
    private Bundle mActivityBundle;

    public PresenterLifecycleManager(PresenterFactory<P> presenterFactory){
        this.mPresenterFactory = presenterFactory;
    }

    public void onRestoreInstanceState(Bundle savedInstanceState){
        this.mActivityBundle = savedInstanceState;
    }

    public void onResume(MvpView view){
        getPresenter();
        if (mPresenter != null)
            mPresenter.attachView(view);
    }

    public void onPause(boolean isFinishing){
        if (mPresenter != null) {
            mPresenter.detachView();
            if (isFinishing) {
                mPresenter.destroy();
                mPresenter = null;
            }
        }
    }

    public Bundle onSaveInstanceState(){
        Bundle bundle = new Bundle();
        if(mPresenter != null){
            Bundle presenterBundle = new Bundle();
            mPresenter.onSaveInstanceState(presenterBundle);
            bundle.putString(KEY_PRESENTER_ID, mPresenter.getID());
            bundle.putBundle(KEY_PRESENTER_STATE, presenterBundle);
        }
        return bundle;
    }

    /**
     * This will return non-null value. Presenter will be created based on previous savedState(if applicable)
     * @return Presenter
     */
    public P getPresenter(){
        if(mPresenter == null){
            if(mActivityBundle != null){
                mPresenter = PresenterStorage.getInstance().get(mActivityBundle.getString(KEY_PRESENTER_ID));
            }
            if(mPresenter == null){     //recreate presenter if not exist in PresenterStorage
                mPresenter = mPresenterFactory.createPresenter();
                if(mActivityBundle != null){    //pass presenter savedInstanceState to newly created presenter
                    mPresenter.create(mActivityBundle.getBundle(KEY_PRESENTER_STATE));
                }
                PresenterStorage.getInstance().add(mPresenter);
            }
        }
        return mPresenter;
    }
}
