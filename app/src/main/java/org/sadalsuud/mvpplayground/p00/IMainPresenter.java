package org.sadalsuud.mvpplayground.p00;

import org.sadalsuud.basemvp.presenter.MvpPresenter;
import org.sadalsuud.basemvp.view.MvpView;

/**
 * Created by fchristysen on 1/28/16.
 */
public interface IMainPresenter<V extends MvpView> extends MvpPresenter<V>{
    void onRefresh();
    void onChooseItem(Class chosenItem);
}
