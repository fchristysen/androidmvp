package org.sadalsuud.mvpplayground.p03;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;

/**
 * Created by fchristysen on 2/17/16.
 */
public class StateViewPager extends ViewPager {
    public StateViewPager(Context context){
        super(context);
    }

    public StateViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        super.dispatchRestoreInstanceState(container);
        if(getAdapter()!=null && getAdapter() instanceof StatefulPagerAdapter){
            StatefulPagerAdapter adapter = (StatefulPagerAdapter) getAdapter();
            for(int i=0; i<adapter.getCount();i++){
                View view = adapter.getView(i);
                if(view!=null) {
                    view.restoreHierarchyState(container);
                }
            }
        }
    }

    public static interface StatefulPagerAdapter{
        View getView(int position);
        int getCount();
    }
}
