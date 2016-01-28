package org.sadalsuud.mvpplayground.p00;

import org.sadalsuud.basemvp.presenter.MvpPresenter;
import org.sadalsuud.basemvp.view.MvpView;

import java.util.List;

import rx.Observable;

/**
 * Created by fchristysen on 1/28/16.
 */
public interface IMainPresenter<V extends MvpView> extends MvpPresenter<V>{
    void refresh();
    void chooseItem(Class chosenItem);
    Observable<List<Class>> getPageList();
}
