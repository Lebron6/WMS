package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/16.
 */
public class OutGoodsResult {
    /**
     * status : 200
     * msg : 确定成功
     * data : 3422
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
