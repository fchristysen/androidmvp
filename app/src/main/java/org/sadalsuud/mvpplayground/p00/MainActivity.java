package org.sadalsuud.mvpplayground.p00;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import org.sadalsuud.mvpplayground.R;
import org.sadalsuud.mvpplayground.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends BaseActivity<MainPresenter>
        implements SwipeRefreshLayout.OnRefreshListener, AdapterView.OnItemClickListener {
    private SwipeRefreshLayout vSwipeLayout;
    private RecyclerView vList;
    private SimpleListAdapter mAdapter;
    private ViewGroup vLoadingLayout;
    private TextView vLoadingText;
    private ViewGroup vInfoLayout;
    private TextView vInfoText;

    //region lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initState();
        initListener();
    }

    public void initView(){
        setContentView(R.layout.main_activity);
        vSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        vList = (RecyclerView) findViewById(R.id.list);
        vLoadingLayout = (ViewGroup) findViewById(R.id.loading_layout);
        vLoadingText = (TextView) findViewById(R.id.loading_text);
        vInfoLayout = (ViewGroup) findViewById(R.id.info_layout);
        vInfoText = (TextView) findViewById(R.id.info_text);
    }

    public void initState(){
        vList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SimpleListAdapter();
        vList.setAdapter(mAdapter);
        vLoadingLayout.setVisibility(View.GONE);
        vInfoLayout.setVisibility(View.GONE);
    }

    public void initListener(){
        vSwipeLayout.setOnRefreshListener(this);
        mAdapter.setOnItemClickListener(this);
    }
    //endregion

    //region listener
    @Override
    public void onRefresh() {
        getPresenter().refresh();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Class chosenItem = mAdapter.getData(position);
        getPresenter().chooseItem(chosenItem);
    }

    //endregion

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    public void showPageList(List<Class> list){
        mAdapter.setData(list);
    }

    public void navigateTo(Class page){
        Intent intent = new Intent(this, page);
        startActivity(intent);
        Toast.makeText(this, "Will go to "+page.getSimpleName(), Toast.LENGTH_SHORT).show();
    }

    public void startLoading(){
        vLoadingLayout.setVisibility(View.VISIBLE);
        vSwipeLayout.setRefreshing(true);
    }

    public void stopLoading(){
        vLoadingLayout.setVisibility(View.GONE);
        vSwipeLayout.setRefreshing(false);
    }

    public void showInfoScreen(String message){
        vInfoLayout.setVisibility(View.VISIBLE);
        vInfoText.setText(message);
    }

    public void hideInfoScreen(){
        vInfoLayout.setVisibility(View.GONE);
        vInfoText.setText("");
    }

    public static class SimpleListAdapter extends RecyclerView.Adapter<SimpleListAdapter.ViewHolder>{
        private List<Class> mData;
        private AdapterView.OnItemClickListener mListener;

        public static class ViewHolder extends RecyclerView.ViewHolder {
            public View vRoot;
            public TextView vText;
            public ViewHolder(View root, TextView text) {
                super(root);
                vRoot = root;
                vText = text;
            }
        }

        public SimpleListAdapter() {
            mData = new ArrayList<>();
        }

        @Override
        public SimpleListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            TextView tv = (TextView) v.findViewById(android.R.id.text1);

            ViewHolder vh = new ViewHolder(v, tv);
            return vh;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,final int position) {
            Class c = mData.get(position);
            holder.vRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        mListener.onItemClick(null, v, position, 0);
                    }
                }
            });
            holder.vText.setText(c.getSimpleName());
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public Class getData(int position){
            return mData.get(position);
        }

        public void setData(List<Class> data){
            clearData();
            mData.addAll(data);
            this.notifyDataSetChanged();
        }

        public void clearData(){
            mData.clear();
            this.notifyDataSetChanged();
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener listener){
            this.mListener = listener;
        }
    }
}
