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
import com.example.bsproperty.bean.UserBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPeopleActivity extends BaseActivity {

    @BindView(R.id.btn_back)
    Button btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.btn_right)
    Button btnRight;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.tv_sex_click)
    TextView tvSexClick;

    private UserBean userBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        tvTitle.setText("新增");
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setText("保存");
    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_add_people;
    }

    @Override
    protected void loadData() {
        userBean = new UserBean();
        userBean.setSex(0);
    }


    @OnClick({R.id.btn_back, R.id.btn_right, R.id.tv_sex_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                String name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    return;
                }
                userBean.setName(name);
                userBean.setMvp(0);
                userBean.setFlag(0);
                userBean.setIsout(false);
                userBean.setAction(1);
                MyApplication.getInstance().getDaoSession().getUserBeanDao().insert(userBean);
                finish();
                break;
            case R.id.tv_sex_click:
                final String[] sex = new String[]{"男","女"};
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("性别").setItems(sex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userBean.setSex(which);
                        tvSexClick.setText(sex[which]);
                    }
                }).show();
                break;
        }
    }
}
