package org.sadalsuud.mvpplayground.p00;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.Presenter;
import org.sadalsuud.mvpplayground.App;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by fchristysen on 1/23/16.
 */
public class MainPresenter extends Presenter<IMainView> implements IMainPresenter<IMainView>{
    public static final int STATE_START = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_RESULT = 3;

    private int mState=STATE_START;
    private MainModelHandler mMainModelHandler;
    private List<Class> mDatas;

    //region lifecycle
    @Override
    public void onCreate(Bundle presenterState) {
        super.onCreate(presenterState);
        mMainModelHandler = App.getMainModelHandler();
//        refresh();
    }

    @Override
    public void onSaveInstanceState(Bundle outPresenterState) {
        super.onSaveInstanceState(outPresenterState);
    }

    @Override
    protected void onViewAttached() {
        super.onViewAttached();
        updateViewState();
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

    public void updateViewState(){
        if(getView()==null)   return;
        if(mState == STATE_START){
            getView().showInfoScreen("Pull down to retrieve the dummy data");
        }else if(mState == STATE_LOADING){
            getView().startLoading();
            getView().hideInfoScreen();
        }else if(mState == STATE_ERROR){
            getView().showInfoScreen("We could not load the data. The random number does not favors you.");
            getView().stopLoading();
        }else if(mState == STATE_RESULT){
            getView().showPageList(mDatas);
            getView().stopLoading();
            getView().hideInfoScreen();
        }
    }

    //region Presenter-View
    public void refresh(){
        mState = STATE_LOADING;
        updateViewState();
        getPageList().subscribe(new Action1<List<Class>>() {
            @Override
            public void call(List<Class> classes) {
                mState = STATE_RESULT;
                mDatas = classes;
                updateViewState();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                mState = STATE_ERROR;
                updateViewState();
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
