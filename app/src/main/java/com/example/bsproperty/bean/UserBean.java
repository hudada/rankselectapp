package com.example.bsproperty.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by wdxc1 on 2018/1/28.
 */

@Entity
public class UserBean implements Serializable {
    static final long serialVersionUID = 42L;

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private int sex;  //0=男
    private int flag;  //0=参与
    private boolean isout;
    private int mvp;
    private int action = 0;  //0=normal,1=add,2=update,3=delete
    @Generated(hash = 1406792108)
    public UserBean(Long id, String name, int sex, int flag, boolean isout, int mvp,
            int action) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.flag = flag;
        this.isout = isout;
        this.mvp = mvp;
        this.action = action;
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getSex() {
        return this.sex;
    }
    public void setSex(int sex) {
        this.sex = sex;
    }
    public int getFlag() {
        return this.flag;
    }
    public void setFlag(int flag) {
        this.flag = flag;
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
    public int getAction() {
        return this.action;
    }
    public void setAction(int action) {
        this.action = action;
    }


}
