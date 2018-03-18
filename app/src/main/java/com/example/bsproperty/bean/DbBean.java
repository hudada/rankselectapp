package com.example.bsproperty.bean;

import java.util.List;

public class DbBean {

    private List<UserBean> userBeans;
    private List<SportsBean> sportsBeans;
    private List<ShareBean> shareBeans;

    public List<UserBean> getUserBeans() {
        return userBeans;
    }

    public void setUserBeans(List<UserBean> userBeans) {
        this.userBeans = userBeans;
    }

    public List<SportsBean> getSportsBeans() {
        return sportsBeans;
    }

    public void setSportsBeans(List<SportsBean> sportsBeans) {
        this.sportsBeans = sportsBeans;
    }

    public List<ShareBean> getShareBeans() {
        return shareBeans;
    }

    public void setShareBeans(List<ShareBean> shareBeans) {
        this.shareBeans = shareBeans;
    }

}
