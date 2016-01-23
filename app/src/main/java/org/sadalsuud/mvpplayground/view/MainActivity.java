package org.sadalsuud.mvpplayground.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;
import org.sadalsuud.mvpplayground.presenter.MainPresenter;


public class MainActivity extends BaseActivity<MainPresenter> {
    private SwipeRefreshLayout vSwipeLayout;
    private RecyclerView vList;
    private ViewGroup vLoadingLayout;
    private TextView vLoadingText;
    private ProgressBar vLoadingBar;
    private ViewGroup vInfoLayout;
    private TextView vInfoText;

    //region lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initState();
    }

    public void initView(){
        setContentView(R.layout.main_activity);
        vSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        vList = (RecyclerView) findViewById(R.id.list);
        vLoadingLayout = (ViewGroup) findViewById(R.id.loading_layout);
        vLoadingText = (TextView) findViewById(R.id.loading_text);
        vLoadingBar = (ProgressBar) findViewById(R.id.loading_bar);
        vInfoLayout = (ViewGroup) findViewById(R.id.info_layout);
        vInfoText = (TextView) findViewById(R.id.info_text);
    }

    public void initState(){
        vLoadingLayout.setVisibility(View.GONE);
        vInfoLayout.setVisibility(View.GONE);
    }
    //endregion

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void showLoadingScreen(){
        vLoadingLayout.setVisibility(View.VISIBLE);
        vLoadingBar.setIndeterminate(true);
    }

    public void hideLoadingScreen(){
        vLoadingLayout.setVisibility(View.GONE);
    }

    public void showInfoScreen(String message){
        vInfoLayout.setVisibility(View.VISIBLE);
        vInfoText.setText(message);
    }

    public void hideInfoScreen(){
        vInfoLayout.setVisibility(View.GONE);
        vInfoText.setText("");
    }


}
