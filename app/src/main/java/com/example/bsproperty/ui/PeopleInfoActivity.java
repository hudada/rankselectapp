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

public class PeopleInfoActivity extends BaseActivity {
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
    @BindView(R.id.tv_out_click)
    TextView tvOutClick;
    @BindView(R.id.tv_flag_click)
    TextView tvFlagClick;
    @BindView(R.id.et_mvp)
    EditText etMvp;

    private UserBean userBean;

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_people_info;
    }

    @Override
    protected void loadData() {
        userBean = (UserBean) getIntent().getSerializableExtra("data");
        tvTitle.setText(userBean.getName());
        btnRight.setVisibility(View.VISIBLE);
        btnRight.setText("保存");

        etName.setText(userBean.getName());
        if (userBean.getSex() == 0) {
            tvSexClick.setText("男");
        } else {
            tvSexClick.setText("女");
        }

        if (userBean.getIsout()) {
            tvOutClick.setText("是");
        } else {
            tvOutClick.setText("否");
        }

        if (userBean.getFlag() == 0) {
            tvFlagClick.setText("是");
        } else {
            tvFlagClick.setText("否");
        }
        etMvp.setText(userBean.getMvp()+"");
    }


    @OnClick({R.id.btn_back, R.id.btn_right, R.id.tv_sex_click, R.id.tv_out_click, R.id.tv_flag_click})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                String name = etName.getText().toString().trim();
                String mvp = etMvp.getText().toString().trim();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(mvp)){
                    return;
                }
                userBean.setName(name);
                userBean.setMvp(Integer.parseInt(mvp));
                userBean.setAction(2);
                MyApplication.getInstance().getDaoSession().getUserBeanDao()
                        .update(userBean);
                finish();
                break;
            case R.id.tv_sex_click:
                final String[] sex = new String[]{"男","女"};
                AlertDialog.Builder builder = new AlertDialog.Builder(PeopleInfoActivity.this);
                builder.setTitle("性别").setItems(sex, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userBean.setSex(which);
                        tvSexClick.setText(sex[which]);
                    }
                }).show();
                break;
            case R.id.tv_out_click:
                final String[] out = new String[]{"是","否"};
                AlertDialog.Builder builder1 = new AlertDialog.Builder(PeopleInfoActivity.this);
                builder1.setTitle("是否离职").setItems(out, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which==0){
                            userBean.setIsout(true);
                        }else{
                            userBean.setIsout(false);
                        }
                        tvOutClick.setText(out[which]);
                    }
                }).show();
                break;
            case R.id.tv_flag_click:
                final String[] flag = new String[]{"是","否"};
                AlertDialog.Builder builder2 = new AlertDialog.Builder(PeopleInfoActivity.this);
                builder2.setTitle("是否参与").setItems(flag, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        userBean.setFlag(which);
                        tvFlagClick.setText(flag[which]);
                    }
                }).show();
                break;
        }
    }
}
