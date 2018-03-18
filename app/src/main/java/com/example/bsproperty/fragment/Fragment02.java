package com.example.bsproperty.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.R;
import com.example.bsproperty.adapter.BaseAdapter;
import com.example.bsproperty.bean.UserBean;
import com.example.bsproperty.bean.UserListBean;
import com.example.bsproperty.net.ApiManager;
import com.example.bsproperty.net.BaseCallBack;
import com.example.bsproperty.net.OkHttpTools;
import com.example.bsproperty.ui.AddPeopleActivity;
import com.example.bsproperty.ui.PeopleInfoActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by John on 2018/3/7.
 */

public class Fragment02 extends BaseFragment {
    @BindView(R.id.rv_list)
    RecyclerView rvList;
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    Unbinder unbinder;

    private ArrayList<UserBean> mData;
    private MyAdapter adapter;

    @Override
    protected void loadData() {
        tvTitle.setText("人员");
        btnBack.setText("同步");
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setText("添加");
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        mData = new ArrayList<>();
        adapter = new MyAdapter(mContext, R.layout.item_user, mData);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Object item, int position) {
                Intent intent = new Intent(mContext, PeopleInfoActivity.class);
                intent.putExtra("data", mData.get(position));
                startActivity(intent);
            }
        });
        rvList.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mData.clear();
        Cursor cursor = MyApplication.getInstance().getDaoSession().getDatabase().rawQuery(
                "select * from USER_BEAN order by isout,flag", new String[]{}
        );
        while (cursor.moveToNext()) {
            UserBean userBean = new UserBean();
            int id = cursor.getColumnIndex("_id");
            int name = cursor.getColumnIndex("NAME");
            int sex = cursor.getColumnIndex("SEX");
            int flag = cursor.getColumnIndex("FLAG");
            int out = cursor.getColumnIndex("ISOUT");
            int mvp = cursor.getColumnIndex("MVP");

            userBean.setId(cursor.getLong(id));
            userBean.setName(cursor.getString(name));
            userBean.setSex(cursor.getInt(sex));
            userBean.setFlag(cursor.getInt(flag));
            userBean.setIsout(cursor.getInt(out) > 0);
            userBean.setMvp(cursor.getInt(mvp));

            mData.add(userBean);
        }
        adapter.notifyDataSetChanged(mData);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_02;
    }

    private void upUserData() {
        final List<UserBean> uData = new ArrayList<>();
        Cursor cursor = MyApplication.getInstance().getDaoSession().getDatabase().rawQuery(
                "select * from USER_BEAN where ACTION > ?", new String[]{0 + ""}
        );
        while (cursor.moveToNext()) {
            UserBean userBean = new UserBean();
            int id = cursor.getColumnIndex("_id");
            int name = cursor.getColumnIndex("NAME");
            int sex = cursor.getColumnIndex("SEX");
            int flag = cursor.getColumnIndex("FLAG");
            int out = cursor.getColumnIndex("ISOUT");
            int mvp = cursor.getColumnIndex("MVP");

            userBean.setId(cursor.getLong(id));
            userBean.setName(cursor.getString(name));
            userBean.setSex(cursor.getInt(sex));
            userBean.setFlag(cursor.getInt(flag));
            userBean.setIsout(cursor.getInt(out) > 0);
            userBean.setMvp(cursor.getInt(mvp));

            uData.add(userBean);
        }
        if (uData.size() > 0) {
            OkHttpTools.postJson(mContext, ApiManager.ADD_USER,
                    uData)
                    .build()
                    .execute(new BaseCallBack<UserListBean>(mContext, UserListBean.class) {
                        @Override
                        public void onResponse(UserListBean userListBean) {
                            for (UserBean uDatum : uData) {
                                uDatum.setAction(0);
                                MyApplication.getInstance().getDaoSession()
                                        .getUserBeanDao().update(uDatum);
                            }
                        }
                    });
        } else {
            showToast("没有要同步的数据");
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_right})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_back:
                upUserData();
                break;
            case R.id.btn_right:
                startActivity(new Intent(mContext, AddPeopleActivity.class));
                break;
        }
    }

    private class MyAdapter extends BaseAdapter<UserBean> {

        public MyAdapter(Context context, int layoutId, ArrayList<UserBean> data) {
            super(context, layoutId, data);
        }

        @Override
        public void initItemView(BaseViewHolder holder, UserBean userBean, int position) {
            holder.setText(R.id.tv_name, userBean.getName());
            TextView tv_flag = (TextView) holder.getView(R.id.tv_flag);
            if (userBean.getFlag() == 0) {
                tv_flag.setText("是否参与：是");
                tv_flag.setTextColor(Color.RED);
            } else {
                tv_flag.setText("是否参与：否");
                tv_flag.setTextColor(Color.BLACK);
            }
            holder.setText(R.id.tv_mvp, "获奖次数" + userBean.getMvp() + "次");
            if (userBean.getSex() == 0) {
                holder.setText(R.id.tv_sex, "男");
            } else {
                holder.setText(R.id.tv_sex, "女");
            }
            if (userBean.getIsout()) {
                holder.setText(R.id.tv_out, "已离职");
                holder.getView(R.id.rl_root).setBackgroundColor(getResources().getColor(R.color.line));
            } else {
                holder.setText(R.id.tv_out, "未离职");
                holder.getView(R.id.rl_root).setBackgroundResource(R.drawable.btn_ripple);
            }
        }
    }
}

