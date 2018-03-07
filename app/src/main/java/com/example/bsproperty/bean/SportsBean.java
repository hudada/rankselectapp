package com.example.bsproperty.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Created by wdxc1 on 2018/1/28.
 */

@Entity
public class SportsBean {
    @Id(autoincrement = true)
    private Long id;
    private Long webid;
    private Long start;
    private Long end;
    private Long sid;
    private String gift;
    @Generated(hash = 1227989864)
    public SportsBean(Long id, Long webid, Long start, Long end, Long sid,
            String gift) {
        this.id = id;
        this.webid = webid;
        this.start = start;
        this.end = end;
        this.sid = sid;
        this.gift = gift;
    }
    @Generated(hash = 2087731695)
    public SportsBean() {
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
    public Long getStart() {
        return this.start;
    }
    public void setStart(Long start) {
        this.start = start;
    }
    public Long getEnd() {
        return this.end;
    }
    public void setEnd(Long end) {
        this.end = end;
    }
    public Long getSid() {
        return this.sid;
    }
    public void setSid(Long sid) {
        this.sid = sid;
    }
    public String getGift() {
        return this.gift;
    }
    public void setGift(String gift) {
        this.gift = gift;
    }

}
