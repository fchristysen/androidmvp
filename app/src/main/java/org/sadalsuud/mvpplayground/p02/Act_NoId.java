package org.sadalsuud.mvpplayground.p02;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;

/**
 * Created by fchristysen on 1/27/16.
 * This page showcases saving state mechanism for views added from code(don't have id)
 * To enable android automatically save view state, those view id must be set(id must be consistent)
 */
public class Act_NoId extends BaseActivity<Prs_NoId> implements CompoundButton.OnCheckedChangeListener, TextWatcher {
    private ViewGroup vRoot;
    private EditText vField1;
    private CheckBox vBox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initState();
        initListener();
    }

    public void initView(){
        setContentView(R.layout.act_noid);
        vRoot = (ViewGroup)findViewById(R.id.root);
        vField1 = new EditText(this);
        vBox1 = new CheckBox(this);
        vRoot.addView(vField1);
        vRoot.addView(vBox1);
    }

    public void initState(){
        vField1.setId(R.id.text1);
        vBox1.setId(R.id.loading_text);

        vBox1.setChecked(true);
    }

    public void initListener(){
        vField1.addTextChangedListener(this);
        vBox1.setOnCheckedChangeListener(this);
    }

    //region listener
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        getPresenter().checkChanged(isChecked);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        getPresenter().textChanged(s.toString());
    }
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
    public Prs_NoId createPresenter() {
        return new Prs_NoId();
    }
}
