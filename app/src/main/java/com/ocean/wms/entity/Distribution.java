package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/8.
 */
public class Distribution {

    /**
     * status : 200
     * msg : 储位分配成功
     * data : {"waid":"7805","warearea":"113-ABBB-1-1","wareareanum":100}
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
         * waid : 7805
         * warearea : 113-ABBB-1-1
         * wareareanum : 100
         */

        private String waid;
        private String warearea;
        private int wareareanum;

        public String getWaid() {
            return waid;
        }

        public void setWaid(String waid) {
            this.waid = waid;
        }

        public String getWarearea() {
            return warearea;
        }

        public void setWarearea(String warearea) {
            this.warearea = warearea;
        }

        public int getWareareanum() {
            return wareareanum;
        }

        public void setWareareanum(int wareareanum) {
            this.wareareanum = wareareanum;
        }
    }
}
