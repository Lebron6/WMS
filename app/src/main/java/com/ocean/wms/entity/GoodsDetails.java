package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/16.
 */
public class GoodsDetails {

    /**
     * status : 200
     * msg : 查询成功
     * data : [{"id":"18700","cgID":"1585","goodscoding":"S11A11027AA","goods":"左前门上铰链总成","whID":"113","supplierID":"66","waID":"7784","warearea":"113-DJ-1-5","outnum":"50","Cbatch":"202001101418","hu":"R202001101418382815850","isok":"0"},{"id":"18701","cgID":"1585","goodscoding":"S11A11027AA","goods":"左前门上铰链总成","whID":"113","supplierID":"66","waID":"7784","warearea":"113-DJ-1-5","outnum":"50","Cbatch":"202001101418","hu":"R202001101418382815851","isok":"0"}]
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
         * id : 18700
         * cgID : 1585
         * goodscoding : S11A11027AA
         * goods : 左前门上铰链总成
         * whID : 113
         * supplierID : 66
         * waID : 7784
         * warearea : 113-DJ-1-5
         * outnum : 50
         * Cbatch : 202001101418
         * hu : R202001101418382815850
         * isok : 0
         */

        private String id;
        private String cgID;
        private String goodscoding;
        private String goods;
        private String whID;
        private String supplierID;
        private String waID;
        private String warearea;
        private String outnum;
        private String Cbatch;
        private String hu;
        private String isok;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCgID() {
            return cgID;
        }

        public void setCgID(String cgID) {
            this.cgID = cgID;
        }

        public String getGoodscoding() {
            return goodscoding;
        }

        public void setGoodscoding(String goodscoding) {
            this.goodscoding = goodscoding;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getWhID() {
            return whID;
        }

        public void setWhID(String whID) {
            this.whID = whID;
        }

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
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

        public String getOutnum() {
            return outnum;
        }

        public void setOutnum(String outnum) {
            this.outnum = outnum;
        }

        public String getCbatch() {
            return Cbatch;
        }

        public void setCbatch(String Cbatch) {
            this.Cbatch = Cbatch;
        }

        public String getHu() {
            return hu;
        }

        public void setHu(String hu) {
            this.hu = hu;
        }

        public String getIsok() {
            return isok;
        }

        public void setIsok(String isok) {
            this.isok = isok;
        }
    }
}
