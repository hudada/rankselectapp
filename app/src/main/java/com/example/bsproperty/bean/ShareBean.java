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
    private Long webid;
    private Long uid;
    private String title;
    private Long time;
    private Double score;
    @Generated(hash = 814937284)
    public ShareBean(Long id, Long webid, Long uid, String title, Long time,
            Double score) {
        this.id = id;
        this.webid = webid;
        this.uid = uid;
        this.title = title;
        this.time = time;
        this.score = score;
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
    public Long getWebid() {
        return this.webid;
    }
    public void setWebid(Long webid) {
        this.webid = webid;
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

}
