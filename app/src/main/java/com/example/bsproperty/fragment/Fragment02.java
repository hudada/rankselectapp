package com.example.bsproperty.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.R;
import com.example.bsproperty.adapter.BaseAdapter;
import com.example.bsproperty.bean.UserBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by John on 2018/3/7.
 */

public class Fragment02 extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private ArrayList<UserBean> mData;
    private MyAdapter adapter;

    @Override
    protected void loadData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        mData = (ArrayList<UserBean>) MyApplication.getInstance().getDaoSession().getUserBeanDao().loadAll();
        adapter = new MyAdapter(mContext,R.layout.item_user,mData);
        rvList.setAdapter(adapter);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_02;
    }

    private class MyAdapter extends BaseAdapter<UserBean>{

        public MyAdapter(Context context, int layoutId, ArrayList<UserBean> data) {
            super(context, layoutId, data);
        }

        @Override
        public void initItemView(BaseViewHolder holder, UserBean userBean, int position) {
            holder.setText(R.id.tv_name,userBean.getName());
        }
    }
}

