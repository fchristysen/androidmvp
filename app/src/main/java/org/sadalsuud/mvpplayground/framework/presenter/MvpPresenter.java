package org.sadalsuud.mvpplayground.framework.presenter;

import android.os.Bundle;
import android.support.annotation.NonNull;

import org.sadalsuud.mvpplayground.framework.view.MvpView;

/**
 * Created by fchristysen on 1/21/16.
 */
public interface MvpPresenter<V extends MvpView> {

    String getID();

    void attachView(@NonNull V view);

    void detachView();

    V getView();

    void create(Bundle savedPresenterState);

    void onSaveInstanceState(Bundle outPresenterState);

    void destroy();

    void addOnDestroyListener(OnDestroyListener listener);

    boolean removeOnDestroyListener(OnDestroyListener listener);

    interface OnDestroyListener{
        void onDestroy(String presenterId);
    }
}
