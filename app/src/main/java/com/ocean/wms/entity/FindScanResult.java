package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/8.
 */
public class FindScanResult {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"id":"7712","waID":"7430","warearea":"107-G-4-1"}
     */

    private int status;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 7712
         * waID : 7430
         * warearea : 107-G-4-1
         */

        private String id;
        private String waID;
        private String warearea;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWaID() {
            return waID;
        }

        public void setWaID(String waID) {
            this.waID = waID;
        }

        public String getWarearea() {
            return warearea;
        }

        public void setWarearea(String warearea) {
            this.warearea = warearea;
        }
    }
}
