package com.example.bsproperty.fragment;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.bsproperty.bean.ShareAndUserBean;
import com.example.bsproperty.bean.ShareBean;
import com.example.bsproperty.bean.UserBean;
import com.example.bsproperty.bean.UserListBean;
import com.example.bsproperty.net.ApiManager;
import com.example.bsproperty.net.BaseCallBack;
import com.example.bsproperty.net.OkHttpTools;
import com.example.bsproperty.ui.AddPeopleActivity;
import com.example.bsproperty.ui.AddShareActivity;
import com.example.bsproperty.ui.ShareInfoActivity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by John on 2018/3/7.
 */

public class Fragment01 extends BaseFragment {
    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.rv_list)
    RecyclerView rvList;

    private MyAdapter adapter;
    private ArrayList<ShareAndUserBean> mData;

    @Override
    protected void loadData() {
        mData = new ArrayList<>();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        mData.clear();
        Cursor cursor = MyApplication.getInstance().getDaoSession().getDatabase().rawQuery(
                "select s.SCORE2,s.INFO,s.uid,s._id,s.title,s.time,s.score,u.name from SHARE_BEAN s left join " +
                        "USER_BEAN u on s.uid = u._id order by s.time desc", new String[]{}
        );
        while (cursor.moveToNext()) {
            ShareAndUserBean bean = new ShareAndUserBean();
            int sid = cursor.getColumnIndex("_id");
            int uid = cursor.getColumnIndex("UID");
            int title = cursor.getColumnIndex("TITLE");
            int time = cursor.getColumnIndex("TIME");
            int score = cursor.getColumnIndex("SCORE");
            int score2 = cursor.getColumnIndex("SCORE2");
            int name = cursor.getColumnIndex("NAME");
            int info = cursor.getColumnIndex("INFO");

            bean.setSid(cursor.getLong(sid));
            bean.setUid(cursor.getLong(uid));
            bean.setTitle(cursor.getString(title));
            bean.setTime(cursor.getLong(time));
            bean.setScore(cursor.getDouble(score));
            bean.setScore2(cursor.getDouble(score2));
            bean.setName(cursor.getString(name));
            bean.setInfo(cursor.getString(info));

            mData.add(bean);
        }
        adapter.notifyDataSetChanged(mData);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTitle.setText("分享");
        btnBack.setText("同步");
        btnRight.setText("新增");
        btnRight.setVisibility(View.VISIBLE);

        rvList.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MyAdapter(mContext, R.layout.item_share, mData);
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, Object item, int position) {
                Intent intent = new Intent(mContext, ShareInfoActivity.class);
                intent.putExtra("data", mData.get(position));
                startActivity(intent);
            }
        });
        rvList.setAdapter(adapter);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_01;
    }

    @OnClick({R.id.btn_back, R.id.btn_right})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.btn_back:
                upUserData();
                break;
            case R.id.btn_right:
                startActivity(new Intent(mContext, AddShareActivity.class));
                break;
        }
    }

    private void upUserData() {
        final List<ShareBean> uData = new ArrayList<>();
        Cursor cursor = MyApplication.getInstance().getDaoSession().getDatabase().rawQuery(
                "select * from SHARE_BEAN where ACTION > ?", new String[]{0 + ""}
        );
        while (cursor.moveToNext()) {
            ShareBean shareBean = new ShareBean();
            int id = cursor.getColumnIndex("_id");
            int uid = cursor.getColumnIndex("UID");
            int title = cursor.getColumnIndex("TITLE");
            int time = cursor.getColumnIndex("TIME");
            int score = cursor.getColumnIndex("SCORE");
            int score2 = cursor.getColumnIndex("SCORE2");
            int info = cursor.getColumnIndex("INFO");

            shareBean.setId(cursor.getLong(id));
            shareBean.setUid(cursor.getLong(uid));
            shareBean.setTitle(cursor.getString(title));
            shareBean.setTime(cursor.getLong(time));
            shareBean.setScore(cursor.getDouble(score));
            shareBean.setScore2(cursor.getDouble(score2));
            shareBean.setInfo(cursor.getString(info));

            uData.add(shareBean);
        }
        if (uData.size() > 0) {
            OkHttpTools.postJson(mContext, ApiManager.ADD_SHARE,
                    uData)
                    .build()
                    .execute(new BaseCallBack<UserListBean>(mContext, UserListBean.class) {
                        @Override
                        public void onResponse(UserListBean userListBean) {
                            for (ShareBean uDatum : uData) {
                                uDatum.setAction(0);
                                MyApplication.getInstance().getDaoSession()
                                        .getShareBeanDao().update(uDatum);
                            }
                        }
                    });
        } else {
            showToast("没有要同步的数据");
        }
    }

    private class MyAdapter extends BaseAdapter<ShareAndUserBean> {

        public MyAdapter(Context context, int layoutId, ArrayList<ShareAndUserBean> data) {
            super(context, layoutId, data);
        }

        @Override
        public void initItemView(BaseViewHolder holder, ShareAndUserBean shareAndUserBean, int position) {
            holder.setText(R.id.tv_title, shareAndUserBean.getTitle());
            holder.setText(R.id.tv_name, shareAndUserBean.getName());
            holder.setText(R.id.tv_score, shareAndUserBean.getScore() + "分");
            if (shareAndUserBean.getTime() != null && shareAndUserBean.getTime() > 0) {
                holder.setText(R.id.tv_time, MyApplication.getmFormat().format(new Date(shareAndUserBean.getTime())));
            }else{
                holder.setText(R.id.tv_time, "忘了");
            }
        }
    }
}
