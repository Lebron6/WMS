package com.ocean.wms.entity;

/**
 * Created by James on 2020/3/17.
 */
public class SearchResult {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"id":"18921","waID":"7779","corpcode":"8320139","goods":"23584040","Cbatch":"202003091456","warearea":"114-JH-1-10","num":"142"}
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
         * id : 18921
         * waID : 7779
         * corpcode : 8320139
         * goods : 23584040
         * Cbatch : 202003091456
         * warearea : 114-JH-1-10
         * num : 142
         */

        private String id;
        private String waID;
        private String corpcode;
        private String goods;
        private String Cbatch;
        private String warearea;
        private String num;

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

        public String getCorpcode() {
            return corpcode;
        }

        public void setCorpcode(String corpcode) {
            this.corpcode = corpcode;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getCbatch() {
            return Cbatch;
        }

        public void setCbatch(String Cbatch) {
            this.Cbatch = Cbatch;
        }

        public String getWarearea() {
            return warearea;
        }

        public void setWarearea(String warearea) {
            this.warearea = warearea;
        }

        public String getNum() {
            return num;
        }

        public void setNum(String num) {
            this.num = num;
        }
    }
}
