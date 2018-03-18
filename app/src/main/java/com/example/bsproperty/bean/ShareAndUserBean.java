package com.example.bsproperty.bean;

import java.io.Serializable;

/**
 * Created by wdxc1 on 2018/3/16.
 */

public class ShareAndUserBean implements Serializable{
    private Long sid;
    private Long uid;
    private String name;
    private String title;
    private Long time;
    private Double score;
    private Double score2;
    private String info;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getScore2() {
        return score2;
    }

    public void setScore2(Double score2) {
        this.score2 = score2;
    }
}
