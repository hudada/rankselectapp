package com.example.bsproperty.ui;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.R;
import com.example.bsproperty.adapter.BaseAdapter;
import com.example.bsproperty.bean.ShareAndUserBean;
import com.example.bsproperty.bean.ShareBean;
import com.example.bsproperty.bean.UserBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShareInfoActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.et_title)
    EditText etTitle;
    @BindView(R.id.tv_name_click)
    TextView tvNameClick;
    @BindView(R.id.tv_time_click)
    TextView tvTimeClick;
    @BindView(R.id.tv_score)
    TextView tvScore;
    @BindView(R.id.tv_score2)
    TextView tvScore2;
    @BindView(R.id.ll_list)
    LinearLayout llList;

    private ShareAndUserBean mData;
    private ArrayList<UserBean> mList;
    private ArrayList<ShareAndUserBean> mInfos;
    private ArrayList<String> mUserName;
    private UserBean selectUser;
    private LayoutInflater mInflater;

    @Override
    protected void initView(Bundle savedInstanceState) {
        btnRight.setText("保存");
        btnRight.setVisibility(View.VISIBLE);
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_share_info;
    }

    @Override
    protected void loadData() {
        mData = (ShareAndUserBean) getIntent().getSerializableExtra("data");
        selectUser = new UserBean();
        selectUser.setId(mData.getUid());
        selectUser.setName(mData.getName());

        mList = (ArrayList<UserBean>) MyApplication.getInstance().getDaoSession().getUserBeanDao().loadAll();
        mUserName = new ArrayList<>();
        mInfos = new ArrayList<>();
        for (UserBean userBean : mList) {
            if (!userBean.getIsout()) {
                mUserName.add(userBean.getName());
                if (!userBean.getId().equals(mData.getUid())) {
                    ShareAndUserBean bean = new ShareAndUserBean();
                    bean.setName(userBean.getName());
                    bean.setUid(userBean.getId());
                    bean.setScore(0d);
                    mInfos.add(bean);
                }
            }
        }

        tvTitle.setText(mData.getTitle());
        etTitle.setText(mData.getTitle());
        tvNameClick.setText(mData.getName());
        tvScore.setText(mData.getScore() + "分");
        tvScore2.setText(mData.getScore2() + "分");
        if (mData.getTime() != null && mData.getTime() > 0) {
            tvTimeClick.setText(MyApplication.getInstance().getmFormat().format(mData.getTime()));
        }


        String info = mData.getInfo();
        if (!TextUtils.isEmpty(info)) {
            String[] infos = info.split(",");
            for (int i = 0; i < infos.length; i++) {
                String ii = infos[i];
                String[] idAs = ii.split(":");
                Long id = Long.parseLong(idAs[0]);
                Double score = Double.parseDouble(idAs[1]);
                for (ShareAndUserBean mInfo : mInfos) {
                    if (mInfo.getUid() == id) {
                        mInfo.setScore(score);
                    }
                }
            }
        }

        initList();
    }

    private void initList() {
        for (int i = 0; i < mInfos.size(); i++) {
            ShareAndUserBean mInfo = mInfos.get(i);
            final int position = i;
            View view = mInflater.inflate(R.layout.item_name_score, null, false);
            TextView name = (TextView) view.findViewById(R.id.tv_name);
            name.setText(mInfo.getName());
            final EditText score = (EditText) view.findViewById(R.id.et_score);

            if (!mInfo.getScore().equals(0d)) {
                score.setText(mInfo.getScore() + "");
            }

            score.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    if (!TextUtils.isEmpty(score.getText().toString().trim())) {
                        mInfos.get(position).setScore(Double.parseDouble(score.getText().toString().trim()));
                    } else {
                        mInfos.get(position).setScore(0d);
                    }
                    initScore();
                }
            });
            llList.addView(view);
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_right, R.id.tv_name_click, R.id.tv_time_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                String title = etTitle.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    showToast("请输入标题");
                    return;
                }
                ShareBean shareBean = new ShareBean();
                shareBean.setId(mData.getSid());
                shareBean.setAction(2);
                shareBean.setScore(Double.parseDouble(tvScore.getText().toString().split("分")[0]));
                shareBean.setTime(mData.getTime());
                shareBean.setTitle(title);
                shareBean.setUid(selectUser.getId());
                StringBuffer buffer = new StringBuffer();
                for (ShareAndUserBean mInfo : mInfos) {
                    buffer.append(mInfo.getUid() + ":");
                    buffer.append(mInfo.getScore() + ",");
                }
                String info = buffer.substring(0, buffer.length() - 1);
                shareBean.setInfo(info);
                shareBean.setScore2(Double.parseDouble(tvScore2.getText().toString().split("分")[0]));
                MyApplication.getInstance().getDaoSession().getShareBeanDao()
                        .update(shareBean);
                finish();
                break;
            case R.id.tv_name_click:
                String[] names = mUserName.toArray(new String[mUserName.size()]);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("员工").setItems(names, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectUser = mList.get(which);
                        tvNameClick.setText(selectUser.getName());
                    }
                }).show();
                break;
            case R.id.tv_time_click:
                final Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        Calendar cc = (Calendar) calendar.clone();
                        cc.set(Calendar.YEAR, year);
                        cc.set(Calendar.MONTH, monthOfYear);
                        cc.set(Calendar.DATE, dayOfMonth);
                        mData.setTime(cc.getTimeInMillis());
                        tvTimeClick.setText(MyApplication.getInstance().getmFormat().format(cc.getTime()));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
                dialog.show();
                break;
        }
    }

    private void initScore() {
        double sum = 0;
        int people = 0;
        for (ShareAndUserBean mInfo : mInfos) {

            if (mInfo.getScore() > 0d) {
                sum += mInfo.getScore();
                people++;
            }
        }
        tvScore.setText(sum + "分");

        tvScore2.setText(sum / people + "分");
    }
}
