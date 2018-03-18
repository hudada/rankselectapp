package com.example.bsproperty.bean;

import java.util.List;

/**
 * Created by wdxc1 on 2018/3/15.
 */

public class DbListBean extends BaseResponse {

    private DbBean data;

    public DbBean getData() {
        return data;
    }

    public void setData(DbBean data) {
        this.data = data;
    }
}
