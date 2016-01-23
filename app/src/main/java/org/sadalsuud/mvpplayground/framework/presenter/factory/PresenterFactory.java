package org.sadalsuud.mvpplayground.framework.presenter.factory;

import org.sadalsuud.mvpplayground.framework.presenter.MvpPresenter;

/**
 * Created by fchristysen on 1/22/16.
 */
public interface PresenterFactory<P extends MvpPresenter> {
    P createPresenter();
}
