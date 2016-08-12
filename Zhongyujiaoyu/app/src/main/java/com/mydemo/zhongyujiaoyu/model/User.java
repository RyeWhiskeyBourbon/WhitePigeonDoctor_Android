package com.mydemo.zhongyujiaoyu.model;


import java.io.Serializable;

/**
 * Created by Chunlei.Li on 15/12/31.
 */
public class User implements Serializable {
    private String email;
    private String pwd;
    private int result;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

}
