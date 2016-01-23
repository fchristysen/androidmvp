package org.sadalsuud.mvpplayground;

import android.os.Bundle;

import org.sadalsuud.mvpplayground.framework.presenter.MvpPresenter;


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
