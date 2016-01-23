package org.sadalsuud.mvpplayground.framework.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.sadalsuud.mvpplayground.framework.view.MvpView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by fchristysen on 1/21/16.
 */
public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    private final String TAG;
    private final String ID;
    private WeakReference<V> mView;
    private ArrayList<MvpPresenter.OnDestroyListener> mListeners;

    public BasePresenter(){
        this.TAG = this.getClass().getSimpleName();
        this.ID = TAG + "/" + System.currentTimeMillis();
        this.mListeners = new ArrayList<>();
    }

    public String getID() {
        return ID;
    }

    @Override
    public void create(Bundle savedPresenterState){
        onCreate(savedPresenterState);
    }

    @Override
    public void attachView(@NonNull V view){
        this.mView = new WeakReference<V>(view);
        onViewAttached();
    }

    @Override
    public void detachView(){
        onDetachView();
        this.mView = null;
        onViewDetached();
    }

    @Override
    public V getView(){
        if(mView == null)
            return null;
        else
            return mView.get();
    }

    @Override
    public void destroy() {
        onDestroy();
        for(OnDestroyListener listener:mListeners){
            listener.onDestroy(getID());
        }
    }

    @Override
    public void addOnDestroyListener(OnDestroyListener listener) {
        mListeners.add(listener);
    }

    @Override
    public boolean removeOnDestroyListener(OnDestroyListener listener) {
        return mListeners.remove(listener);
    }

    //region Lifecycle
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Called when this presenter is created
     * @param presenterState previously saved presenter state(nullable)
     */
    public void onCreate(Bundle presenterState) {
    }

    /**
     * Called when activity's onSaveInstanceState is called
     * @param outPresenterState instanceState specifically for presenter object
     */
    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {

    }

    /**
     * Called after view is attached to this presenter
     */
    protected void onViewAttached(){

    }

    /**
     * Called before view is detached to this presenter
     */
    protected void onDetachView(){

    }

    /**
     * Called after view is detached to this presenter
     */
    protected void onViewDetached(){
    }

    /**
     * Called when the view is finishing
     */
    protected void onDestroy(){
    }
    //endregion

}
