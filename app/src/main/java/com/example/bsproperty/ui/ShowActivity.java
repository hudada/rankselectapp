package com.example.bsproperty.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.hardware.fingerprint.FingerprintManagerCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.bsproperty.MyApplication;
import com.example.bsproperty.R;
import com.example.bsproperty.bean.UserBean;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShowActivity extends BaseActivity {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_rand)
    TextView tvRand;
    @BindView(R.id.btn_rank)
    Button btnRank;
    @BindView(R.id.btn_into)
    Button btnInto;

    private ArrayList<UserBean> mData;
    private ArrayList<UserBean> selectData;

    private Timer timer;
    private Timer dowmTimer;
    private int theTimes = 5;
    private Random random;
    private String who;
    private FingerprintManagerCompat manager;
    private int intoSum = 0;
    private boolean isChecked;


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getRootViewId() {
        return R.layout.activity_show;
    }

    @Override
    protected void loadData() {
        random = new Random();
        selectData = new ArrayList<>();
        mData = (ArrayList<UserBean>) MyApplication.getInstance().getDaoSession().getUserBeanDao().loadAll();
        for (UserBean mDatum : mData) {
            if (mDatum.getFlag() == 0) {
                selectData.add(mDatum);
            }
        }
    }

    @OnClick({R.id.btn_rank, R.id.btn_into,R.id.tv_name})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name:
                if (intoSum > 9) {
                    intoSum = 0;
                    btnInto.setVisibility(View.VISIBLE);
                    manager = FingerprintManagerCompat.from(ShowActivity.this);
                    manager.authenticate(null, 0, null,
                            new MyCallBack(), null);
                } else {
                    intoSum++;
                }
                break;
            case R.id.btn_rank:
                btnRank.setVisibility(View.GONE);
                dowmTimer = new Timer();
                dowmTimer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        if (theTimes == 0) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    stopRand();
                                    dowmTimer.cancel();
                                    theTimes = 5;
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    tvName.setText(theTimes + "");
                                    theTimes--;
                                }
                            });
                        }
                    }
                }, 0, 1000);

                timer = new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                int position = random.nextInt(mData.size());
                                tvRand.setText(mData.get(position).getName());
                            }
                        });
                    }
                }, 0, 100);
                int position = random.nextInt(selectData.size());
                who = selectData.get(position).getName();
                break;
            case R.id.btn_into:
                if (isChecked) {
                    startActivity(new Intent(ShowActivity.this, MainActivity.class));
                }
                break;
        }
    }

    private void stopRand() {
        btnRank.setVisibility(View.VISIBLE);
        timer.cancel();
        tvName.setText(who);
        tvRand.setText(who);
    }

    private class MyCallBack extends FingerprintManagerCompat.AuthenticationCallback {
        private static final String TAG = "MyCallBack";

        // 当出现错误的时候回调此函数，比如多次尝试都失败了的时候，errString是错误信息
        @Override
        public void onAuthenticationError(int errMsgId, CharSequence errString) {
            Log.d(TAG, "onAuthenticationError: " + errString);
        }

        // 当指纹验证失败的时候会回调此函数，失败之后允许多次尝试，失败次数过多会停止响应一段时间然后再停止sensor的工作
        @Override
        public void onAuthenticationFailed() {
            Log.d(TAG, "onAuthenticationFailed: " + "验证失败");
        }

        @Override
        public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
            Log.d(TAG, "onAuthenticationHelp: " + helpString);
        }

        // 当验证的指纹成功时会回调此函数，然后不再监听指纹sensor
        @Override
        public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult
                                                      result) {
            Log.d(TAG, "onAuthenticationSucceeded: " + "验证成功");
            isChecked = true;
        }
    }

}
