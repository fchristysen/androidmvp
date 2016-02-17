package org.sadalsuud.mvpplayground.base;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;

import org.sadalsuud.basemvp.AppUtil;
import org.sadalsuud.basemvp.presenter.MvpPresenter;
import org.sadalsuud.basemvp.presenter.PresenterManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BaseActivity<P extends MvpPresenter> extends AppCompatActivity implements MvpView<P>, PresenterFactory<P>{
    private String TAG;
    protected final String WINDOW_HIERARCHY_TAG = "android:viewHierarchyState";
    protected final String WINDOW_VIEW_TAG = "android:views";

    private PresenterManager<P> mPresenterManager= new PresenterManager(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.TAG = this.getClass().getSimpleName();
        AppUtil.log(TAG + " : " + "onCreate");

        ViewServer.get(this).addWindow(this);
        mPresenterManager.onRestoreInstanceState(savedInstanceState);

        initView();
        initState();
        initListener();
    }

    protected abstract void initView();

    protected abstract void initState();

    protected abstract void initListener();

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(savedInstanceState!=null){
            Bundle hierarchyState = savedInstanceState.getBundle(WINDOW_HIERARCHY_TAG);
            if(hierarchyState!=null){
                SparseArray screenState = hierarchyState.getSparseParcelableArray(WINDOW_VIEW_TAG);
                onRestoreViewState(screenState);
            }
        }
    }

    /**
     * This will be called if activity's savedInstanceState is not null
     * Some child view that's not attached yet will need viewState to call onRestoreViewState manually
     * @param viewState SparseArray containing all view state in current screen
     */
    protected void onRestoreViewState(@Nullable SparseArray<Parcelable> viewState){};

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
        mPresenterManager.onSaveInstanceState(outState);
        AppUtil.log(TAG + " : " + "onSaveInstanceState");
    }

    @Override
    public final P getPresenter() {
        return mPresenterManager.getPresenter();
    }

    @Override
    public abstract P createPresenter();

}
