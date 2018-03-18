package com.example.bsproperty.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wdxc1 on 2018/1/28.
 */

@Entity
public class ShareBean {
    @Id(autoincrement = true)
    private Long id;
    private Long uid;
    private String title;
    private Long time;
    private Double score;
    private Double score2;
    private String info;
    private int action = 0;  //0=normal,1=add,2=update,3=delete
    @Generated(hash = 127387091)
    public ShareBean(Long id, Long uid, String title, Long time, Double score,
            Double score2, String info, int action) {
        this.id = id;
        this.uid = uid;
        this.title = title;
        this.time = time;
        this.score = score;
        this.score2 = score2;
        this.info = info;
        this.action = action;
    }
    @Generated(hash = 1758616687)
    public ShareBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUid() {
        return this.uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Long getTime() {
        return this.time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    public Double getScore() {
        return this.score;
    }
    public void setScore(Double score) {
        this.score = score;
    }
    public Double getScore2() {
        return this.score2;
    }
    public void setScore2(Double score2) {
        this.score2 = score2;
    }
    public String getInfo() {
        return this.info;
    }
    public void setInfo(String info) {
        this.info = info;
    }
    public int getAction() {
        return this.action;
    }
    public void setAction(int action) {
        this.action = action;
    }

}
