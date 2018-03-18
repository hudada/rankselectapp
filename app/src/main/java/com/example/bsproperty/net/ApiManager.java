package com.example.bsproperty.net;

/**
 * Created by yezi on 2018/1/27.
 */

public class ApiManager {

    private static final String HTTP = "http://";
    private static final String IP = "192.168.55.102";
    private static final String PROT = ":8080";
    private static final String HOST = HTTP + IP + PROT;
    private static final String API = "/api";
    private static final String USER = "/user";
    private static final String SHARE = "/share";


    public static final String DB_LIST = HOST + API + "/start";

    public static final String ADD_USER = HOST + API + USER +"/change";

    public static final String ADD_SHARE = HOST + API + SHARE +"/change";
}
