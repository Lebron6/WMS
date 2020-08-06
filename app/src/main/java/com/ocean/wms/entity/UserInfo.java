package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/7.
 */
public class UserInfo {
    /**
     * user : af36U7jM5RpMi9UGH2GimxKQBi36jDclP-P-eNaRg3X9ZHR4-P-0b0UEUglCMA
     * status : 200
     * msg : 登录成功
     */

    private String user;
    private int status;
    private String msg;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
