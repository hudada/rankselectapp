package com.example.bsproperty.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by wdxc1 on 2018/1/28.
 */

@Entity
public class UserBean {
    @Id(autoincrement = true)
    private Long id;
    private Long webid;
    private String name;
    private int flag;
    private boolean isyou;
    private boolean isout;
    private int mvp;
    @Generated(hash = 1214967255)
    public UserBean(Long id, Long webid, String name, int flag, boolean isyou,
            boolean isout, int mvp) {
        this.id = id;
        this.webid = webid;
        this.name = name;
        this.flag = flag;
        this.isyou = isyou;
        this.isout = isout;
        this.mvp = mvp;
    }
    @Generated(hash = 1203313951)
    public UserBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getWebid() {
        return this.webid;
    }
    public void setWebid(Long webid) {
        this.webid = webid;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getFlag() {
        return this.flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
    }
    public boolean getIsyou() {
        return this.isyou;
    }
    public void setIsyou(boolean isyou) {
        this.isyou = isyou;
    }
    public boolean getIsout() {
        return this.isout;
    }
    public void setIsout(boolean isout) {
        this.isout = isout;
    }
    public int getMvp() {
        return this.mvp;
    }
    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

}
