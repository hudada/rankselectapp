package com.example.bsproperty.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.view.ProgressDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yezi on 2018/1/27.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private static Toast mToast;
    private Unbinder unbinder;
    private ProgressDialog progressDialog;
    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootViewId());
        MyApplication.getInstance().addAct(this);
        mContext = this;
        unbinder = ButterKnife.bind(this);
        initView(savedInstanceState);
        loadData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().removeAct(this);
        mContext = null;
        unbinder.unbind();
    }

    protected abstract void initView(Bundle savedInstanceState);

    protected abstract int getRootViewId();

    protected abstract void loadData();

    public void showToast(String str) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    public void showToast(Context context, String str) {
        if (mToast == null) {
            mToast = Toast.makeText(context, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
        }
        mToast.show();
    }

    public void showProgress(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.show();
    }

    public void dismissDialog() {
        progressDialog.dismiss();
    }
}
