package org.sadalsuud.mvpplayground.p00;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.BasePresenter;
import org.sadalsuud.mvpplayground.App;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by fchristysen on 1/23/16.
 */
public class MainPresenter extends BasePresenter<MainActivity> {
    private MainModelHandler mMainModelHandler;
    private boolean isLoading = false;
    private List<Class> mDatas;

    //region lifecycle
    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mMainModelHandler = App.getMainModelHandler();
        refresh();
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        if(isLoading){
            getView().startLoading();
        }
        if(mDatas!=null) {
            getView().showPageList(mDatas);
        }
    }

    @Override
    protected void onDetachView() {
        super.onDetachView();
    }

    @Override
    protected void onViewDetached() {
        super.onViewDetached();
    }
    //endregion

    //region Presenter-View
    public void refresh(){
        isLoading = true;
        if(getView()!=null) {
            getView().startLoading();
        }
        getPageList().subscribe(new Action1<List<Class>>() {
            @Override
            public void call(List<Class> classes) {
                isLoading = false;
                mDatas = classes;
                if(getView()!=null) {
                    getView().showPageList(mDatas);
                    getView().stopLoading();
                }
            }
        });
    }

    public void chooseItem(Class item){
        getView().navigateTo(item);
    }
    //endregion

    //region Presenter-Model
    public Observable<List<Class>> getPageList(){
        return mMainModelHandler.getPageList();
    }
    //endregion
}
