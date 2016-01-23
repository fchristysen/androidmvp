package org.sadalsuud.basemvp.presenter.factory;

import org.sadalsuud.basemvp.presenter.MvpPresenter;

/**
 * Created by fchristysen on 1/22/16.
 */
public @interface RequiresPresenter {
    Class<? extends MvpPresenter> value();
}
