package com.ocean.wms.entity;

/**
 * Created by James on 2020/4/30.
 */
public class SendCarResult {
    /**
     * status : 200
     * msg : 发车成功
     * data : 1
     */

    private int status;
    private String msg;
    private int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
