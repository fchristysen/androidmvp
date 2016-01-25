package org.sadalsuud.basemvp.presenter;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.ViewGroup;

import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/25/16.
 * This class encapsulate additional presenter management specifically for android view
 */
public class ViewPresenterManager<P extends MvpPresenter>{
    public static final String KEY_PARENT_STATE = "parent_state";
    public static final String KEY_CHILDREN_STATE = "children_state";

    private PresenterManager<P> mPresenterManager;

    public ViewPresenterManager(PresenterFactory<P> presenterFactory){
        mPresenterManager = new PresenterManager<>(presenterFactory);
    }

    /**
     * @see PresenterManager#onResume(MvpView view)
     */
    public void onResume(MvpView view){
        mPresenterManager.onResume(view);
    }

    /**
     * @see PresenterManager#onPause(boolean isFinishing)
     */
    public void onPause(boolean isFinishing){
        mPresenterManager.onPause(isFinishing);
    }

    public Parcelable onRestoreInstanceState(ViewGroup viewGroup, Parcelable state){
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            for(int i=0;i<viewGroup.getChildCount();i++){
                viewGroup.getChildAt(i).restoreHierarchyState(bundle.getSparseParcelableArray(KEY_CHILDREN_STATE));
            }
            mPresenterManager.onRestoreInstanceState(bundle);
            return bundle.getParcelable(KEY_PARENT_STATE);
        }
        return state;
    }

    public Parcelable onSaveInstanceState(ViewGroup viewGroup, Parcelable outState){
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, outState);
        SparseArray<Parcelable> childState = new SparseArray();
        for(int i=0;i<viewGroup.getChildCount();i++){
            viewGroup.getChildAt(i).saveHierarchyState(childState);
        }
        bundle.putSparseParcelableArray(KEY_CHILDREN_STATE, childState);
        mPresenterManager.onSaveInstanceState(bundle);
        return bundle;
    }

    /**
     * @see PresenterManager#getPresenter()
     */
    public P getPresenter(){
        return mPresenterManager.getPresenter();
    }
}
