package org.sadalsuud.mvpplayground;

import android.os.Bundle;

import org.sadalsuud.basemvp.presenter.MvpPresenter;
import org.sadalsuud.mvpplayground.view.BaseActivity;


public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public MvpPresenter createPresenter() {
        return null;
    }
}
