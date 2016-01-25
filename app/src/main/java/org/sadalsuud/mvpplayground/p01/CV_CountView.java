package org.sadalsuud.mvpplayground.p01;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sadalsuud.basemvp.presenter.ViewPresenterManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;
import org.sadalsuud.mvpplayground.R;

/**
 * Created by fchristysen on 1/22/16.
 */
public class CV_CountView extends LinearLayout implements MvpView<Prs_CountView>, PresenterFactory<Prs_CountView>, View.OnClickListener {
    private ViewPresenterManager<Prs_CountView> mPresenterManager = new ViewPresenterManager(this);
    private View vRoot;
    private ImageButton vAddButton;
    private ImageButton vSubButton;
    private TextView vText;

    public CV_CountView(Context context, AttributeSet attrs){
        super(context, attrs);
        vRoot = LayoutInflater.from(context).inflate(R.layout.customview_1, this, true);

        initView();
        initState();
        initListener();
    }

    public void initView(){
        vAddButton = (ImageButton) vRoot.findViewById(R.id.button_add);
        vSubButton = (ImageButton) vRoot.findViewById(R.id.button_sub);
        vText = (TextView) vRoot.findViewById(R.id.text1);
    }

    public void initState(){
        vText.setText("0");
    }

    public void initListener(){
        vAddButton.setOnClickListener(this);
        vSubButton.setOnClickListener(this);
    }

    //region lifecycle
    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(mPresenterManager.onRestoreInstanceState(this, state));
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        return mPresenterManager.onSaveInstanceState(this, super.onSaveInstanceState());
    }

    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        mPresenterManager.onResume(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenterManager.onPause(getActivity().isFinishing());
    }
    //endregion

    //region listener
    @Override
    public void onClick(View v) {
        if(v.equals(vAddButton)){
            getPresenter().add();
        }else if(v.equals(vSubButton)){
            getPresenter().sub();
        }
    }
    //endregion

    /**
     * Returns the unwrapped activity of the view or throws an exception.
     * @return an unwrapped activity
     */
    public Activity getActivity() {
        Context context = getContext();
        while (!(context instanceof Activity) && context instanceof ContextWrapper)
            context = ((ContextWrapper)context).getBaseContext();
        if (!(context instanceof Activity))
            throw new IllegalStateException("Expected an activity context, got " + context.getClass().getSimpleName());
        return (Activity)context;
    }

    @Override
    public Prs_CountView createPresenter(){
        return new Prs_CountView();
    }

    @Override
    public Prs_CountView getPresenter() {
        return mPresenterManager.getPresenter();
    }

    public void setCounter(int counter){
        vText.setText("Counter = "+counter);
    }
}
