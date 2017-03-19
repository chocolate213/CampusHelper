package cn.jxzhang.campushelper.constant;

import android.util.Log;

/**
 * Created on 2017-03-18 00:37
 * <p>Title:       RequestAddress</p>
 * <p>Description: [Description]</p>
 *
 * @author <a href=zhangjx_dev@163.com>j.x.zhang</a>
 * @version 1.0
 */

public enum RequestAddress {

    IS_ACCOUNT_EXIST("/account/isAccountExist"),

    LOGIN("/account/login"),

    SAVE_USER("/account/saveUser"),

    SIGN_UP("/account/signUp");

    private final String value;

    private final String REMOTE_SERVICE_URL = "http://192.168.0.101:8080/";

    private final String SERVER_CONTEXT_PATH = "CampusHelper";

    RequestAddress(String value) {
        this.value = value;
    }

    public String value() {
        String url = REMOTE_SERVICE_URL + SERVER_CONTEXT_PATH + this.value;
        Log.d("Request URL : ", url);
        return url;
    }
}
