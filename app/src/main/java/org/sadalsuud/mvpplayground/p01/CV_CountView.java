package org.sadalsuud.mvpplayground.p01;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.sadalsuud.basemvp.presenter.PresenterLifecycleManager;
import org.sadalsuud.basemvp.presenter.factory.PresenterFactory;
import org.sadalsuud.basemvp.view.MvpView;
import org.sadalsuud.mvpplayground.R;

/**
 * Created by fchristysen on 1/22/16.
 */
public class CV_CountView extends LinearLayout implements MvpView<Prs_CountView>, PresenterFactory<Prs_CountView>, View.OnClickListener {
    public static final String KEY_PARENT_STATE = "parent_state";
    public static final String KEY_CHILDREN_STATE = "children_state";

    private PresenterLifecycleManager<Prs_CountView> mPresenterLifecycleManager = new PresenterLifecycleManager(this);
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
        if(state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(bundle.getParcelable(KEY_PARENT_STATE));
            for(int i=0;i<getChildCount();i++){
                getChildAt(i).restoreHierarchyState(bundle.getSparseParcelableArray(KEY_CHILDREN_STATE));
            }
            mPresenterLifecycleManager.onRestoreInstanceState(bundle);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_PARENT_STATE, super.onSaveInstanceState());
        SparseArray<Parcelable> childState = new SparseArray();
        for(int i=0;i<getChildCount();i++){
            getChildAt(i).saveHierarchyState(childState);
        }
        bundle.putSparseParcelableArray(KEY_CHILDREN_STATE, childState);
        mPresenterLifecycleManager.onSaveInstanceState(bundle);
        return bundle;
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
        mPresenterLifecycleManager.onResume(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mPresenterLifecycleManager.onPause(getActivity().isFinishing());
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
        return mPresenterLifecycleManager.getPresenter();
    }

    public void setCounter(int counter){
        vText.setText("Counter = "+counter);
    }
}
