package org.sadalsuud.mvpplayground.p00;

import org.sadalsuud.basemvp.presenter.MvpPresenter;
import org.sadalsuud.basemvp.view.MvpView;

import java.util.List;

/**
 * Created by fchristysen on 1/28/16.
 */
public interface IMainView<P extends MvpPresenter> extends MvpView<P>{
    void showPageList(List<Class> list);
    void navigateTo(Class page);
    void startLoading();
    void stopLoading();
    void showInfoScreen(String message);
    void hideInfoScreen();
}
