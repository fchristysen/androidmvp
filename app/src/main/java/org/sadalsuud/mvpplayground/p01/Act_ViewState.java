package org.sadalsuud.mvpplayground.p01;

import android.os.Bundle;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * This page showcases the save state in custom view and it's presenter
 * notes : saving instance state for a custom view could be problematic if the view is used
 *      several times in one page. Therefore state container for each custom view needs to be encapsulated
 */
public class Act_ViewState extends BaseActivity<Prs_ViewState>{
    @Bind(R.id.countview_1) protected CV_CountView vCount1;
    @Bind(R.id.countview_2) protected CV_CountView vCount2;

    //region lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initState();
        initListener();
    }

    public void initView(){
        setContentView(R.layout.act_viewstate);
        ButterKnife.bind(this);
    }

    public void initState(){

    }

    public void initListener(){
    }
    //endregion

    @Override
    public Prs_ViewState createPresenter() {
        return new Prs_ViewState();
    }

}
