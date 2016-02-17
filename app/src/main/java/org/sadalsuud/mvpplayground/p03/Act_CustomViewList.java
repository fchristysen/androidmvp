package org.sadalsuud.mvpplayground.p03;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.SparseArray;
import android.view.ViewGroup;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;
import org.sadalsuud.mvpplayground.p01.CV_CountView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fchristysen on 1/27/16.
 * This page showcases saving state mechanism for views added from code(don't have id)
 * To enable android automatically save view state, those view id must be set(id must be consistent)
 */
public class Act_CustomViewList extends BaseActivity<Prs_CustomViewList> {
    @Bind(R.id.root) protected ViewGroup vRoot;
    @Bind(R.id.viewPager) protected ViewPager vPager;
    private ViewPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initState();
        restoreState(savedInstanceState);
        initListener();
    }

    public void initView(){
        setContentView(R.layout.act_customviewlist);
        ButterKnife.bind(this);
    }

    public void initState(){
        CV_CountView vCV1 = new CV_CountView(this);
        CV_CountView vCV2 = new CV_CountView(this);
        vCV1.setId(R.id.countview_1);
        vCV2.setId(R.id.countview_2);

        mAdapter = new ViewPagerAdapter();
        mAdapter.addView(vCV1);
        mAdapter.addView(vCV2);
        vPager.setAdapter(mAdapter);
    }

    public void restoreState(Bundle savedInstanceState){
        if(savedInstanceState!=null) {
            SparseArray screenState = savedInstanceState.getBundle("android:viewHierarchyState").getSparseParcelableArray("android:views");
            mAdapter.getView(0).restoreHierarchyState((SparseArray<Parcelable>)screenState);
            mAdapter.getView(1).restoreHierarchyState((SparseArray<Parcelable>)screenState);
        }
    }

    public void initListener(){
    }

    //region listener
    //endregion

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public Prs_CustomViewList createPresenter() {
        return new Prs_CustomViewList();
    }
}
