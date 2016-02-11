package org.sadalsuud.mvpplayground.p00;

import android.widget.Toast;

import org.sadalsuud.basemvp.presenter.Presenter;

import java.util.ArrayList;

/**
 * Created by fchristysen on 2/11/16.
 */
public class MainPresenterTest extends Presenter<IMainView> implements IMainPresenter<IMainView> {

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
    }

    @Override
    public void onRefresh() {
        Toast.makeText(getView().getContext(), "Refresh now", Toast.LENGTH_SHORT).show();
        ArrayList<Class> mocks = new ArrayList<>();
        for(int i=0;i<100;i++)
            mocks.add(MainPresenterTest.class);
        getView().showPageList(mocks);
    }

    @Override
    public void onChooseItem(Class chosenItem) {
        Toast.makeText(getView().getContext(), "Choose "+ chosenItem.getSimpleName(), Toast.LENGTH_SHORT).show();
    }
}
