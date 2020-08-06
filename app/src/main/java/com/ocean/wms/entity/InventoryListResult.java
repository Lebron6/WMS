package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/17.
 */
public class InventoryListResult {
    /**
     * status : 200
     * msg : 查询成功
     * data : [{"dcwID":"735","supplierID":"63","warehouse":null,"whid":"113","supplier":"贵州凯峰科技有限责任公司","Cname":"左前座椅总成（黑色PVC）","goodscoding":"S11341P01AA02","Cmodel":null,"reserve":"200","supnum":"0","cgID":"1584","freeze":"100","corpcode":"GZKF","allnum":400}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * dcwID : 735
         * supplierID : 63
         * warehouse : null
         * whid : 113
         * supplier : 贵州凯峰科技有限责任公司
         * Cname : 左前座椅总成（黑色PVC）
         * goodscoding : S11341P01AA02
         * Cmodel : null
         * reserve : 200
         * supnum : 0
         * cgID : 1584
         * freeze : 100
         * corpcode : GZKF
         * allnum : 400
         */

        private String dcwID;
        private String supplierID;
        private Object warehouse;
        private String whid;
        private String supplier;
        private String Cname;
        private String goodscoding;
        private Object Cmodel;
        private String reserve;
        private String supnum;
        private String cgID;
        private String freeze;
        private String corpcode;
        private int allnum;

        public String getDcwID() {
            return dcwID;
        }

        public void setDcwID(String dcwID) {
            this.dcwID = dcwID;
        }

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
        }

        public Object getWarehouse() {
            return warehouse;
        }

        public void setWarehouse(Object warehouse) {
            this.warehouse = warehouse;
        }

        public String getWhid() {
            return whid;
        }

        public void setWhid(String whid) {
            this.whid = whid;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getCname() {
            return Cname;
        }

        public void setCname(String Cname) {
            this.Cname = Cname;
        }

        public String getGoodscoding() {
            return goodscoding;
        }

        public void setGoodscoding(String goodscoding) {
            this.goodscoding = goodscoding;
        }

        public Object getCmodel() {
            return Cmodel;
        }

        public void setCmodel(Object Cmodel) {
            this.Cmodel = Cmodel;
        }

        public String getReserve() {
            return reserve;
        }

        public void setReserve(String reserve) {
            this.reserve = reserve;
        }

        public String getSupnum() {
            return supnum;
        }

        public void setSupnum(String supnum) {
            this.supnum = supnum;
        }

        public String getCgID() {
            return cgID;
        }

        public void setCgID(String cgID) {
            this.cgID = cgID;
        }

        public String getFreeze() {
            return freeze;
        }

        public void setFreeze(String freeze) {
            this.freeze = freeze;
        }

        public String getCorpcode() {
            return corpcode;
        }

        public void setCorpcode(String corpcode) {
            this.corpcode = corpcode;
        }

        public int getAllnum() {
            return allnum;
        }

        public void setAllnum(int allnum) {
            this.allnum = allnum;
        }
    }
}
