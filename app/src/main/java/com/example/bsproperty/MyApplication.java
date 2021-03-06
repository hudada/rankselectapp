package com.example.bsproperty;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.bsproperty.bean.UserBean;
import com.example.bsproperty.greendao.DaoMaster;
import com.example.bsproperty.greendao.DaoSession;
import com.example.bsproperty.ui.BaseActivity;
import com.facebook.stetho.Stetho;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.log.LoggerInterceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by yezi on 2018/1/27.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private static ArrayList<BaseActivity> activities;

    private static DaoMaster.DevOpenHelper mHelper;
    private static SQLiteDatabase db;
    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    private static DateFormat mFormat;

    private UserBean userBean;

    @Override
    public void onCreate() {
        super.onCreate();
        activities = new ArrayList<>();
        mFormat = new SimpleDateFormat("yyyy-MM-dd");
//        chrome://inspect/#devices
        Stetho.initializeWithDefaults(this);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("hdd"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .build();

        OkHttpUtils.initClient(okHttpClient);
        instance = new MyApplication();
        setDatabase();
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            instance = new MyApplication();
        }
        return instance;
    }

    public static DateFormat getmFormat() {
        return mFormat;
    }

    public void addAct(BaseActivity activity){
        activities.add(activity);
    }

    public void removeAct(BaseActivity activity){
        activities.remove(activity);
    }

    public void clearAct(){
        for (BaseActivity activity : activities) {
            activity.finish();
        }
    }

    private void setDatabase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        mHelper = new DaoMaster.DevOpenHelper(this, "rank_db", null);
        db = mHelper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }

    public SQLiteDatabase getDb() {
        return db;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

}
