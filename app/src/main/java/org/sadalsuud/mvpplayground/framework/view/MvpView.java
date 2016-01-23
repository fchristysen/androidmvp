package org.sadalsuud.mvpplayground.framework.view;

import org.sadalsuud.mvpplayground.framework.presenter.MvpPresenter;

/**
 * Created by fchristysen on 1/21/16.
 */
public interface MvpView<P extends MvpPresenter> {
    /**
     * Returns a current attached presenter.
     * This method is guaranteed to return a non-null value between
     * onCreate/onPause and onAttachedToWindow/onDetachedFromWindow calls
     *
     * @return a currently attached presenter or null.
     */
    P getPresenter();
}
