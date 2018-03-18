package com.example.bsproperty.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.R;
import com.example.bsproperty.bean.ShareAndUserBean;
import com.example.bsproperty.bean.ShareBean;
import com.example.bsproperty.bean.UserBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShareActivity extends BaseActivity {

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

    private ArrayList<String> mUserName;
    private UserBean selectUser;
    private ArrayList<UserBean> mList;

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTitle.setText("新增");
        btnRight.setText("保存");
        btnRight.setVisibility(View.VISIBLE);
    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_add_share;
    }

    @Override
    protected void loadData() {
        mList = (ArrayList<UserBean>) MyApplication.getInstance().getDaoSession().getUserBeanDao().loadAll();
        mUserName = new ArrayList<>();
        for (UserBean userBean : mList) {
            mUserName.add(userBean.getName());
        }
    }

    @OnClick({R.id.btn_back, R.id.btn_right, R.id.tv_name_click})
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
                shareBean.setAction(1);
                shareBean.setTime(System.currentTimeMillis());
                shareBean.setTitle(title);
                shareBean.setUid(selectUser.getId());
                MyApplication.getInstance().getDaoSession().getShareBeanDao()
                        .save(shareBean);
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
        }
    }
}
