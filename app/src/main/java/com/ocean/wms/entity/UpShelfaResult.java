package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/8.
 */
public class UpShelfaResult {
    /**
     * status : 200
     * msg : 上架成功
     */

    private int status;
    private String msg;

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
