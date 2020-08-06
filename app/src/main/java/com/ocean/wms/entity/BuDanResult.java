package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/10.
 */
public class BuDanResult {
    /**
     * status : 200
     * msg : 验证成功
     * data : 7744
     */

    private int status;
    private String msg;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
