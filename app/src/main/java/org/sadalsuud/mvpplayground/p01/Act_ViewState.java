package org.sadalsuud.mvpplayground.p01;

import android.os.Bundle;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;


public class Act_ViewState extends BaseActivity<Prs_ViewState>{
    private CV_CountView vCount1;
    private CV_CountView vCount2;

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
        vCount1 = (CV_CountView) findViewById(R.id.countview_1);
        vCount2 = (CV_CountView) findViewById(R.id.countview_2);
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
