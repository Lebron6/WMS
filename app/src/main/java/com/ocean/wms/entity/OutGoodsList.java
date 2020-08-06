package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/10.
 */
public class OutGoodsList {
    /**
     * status : 200
     * msg : 查询成功
     * data : [{"outID":"2501","clientID":"66","client":"1212","linkman":"111","handset":"111","outcode":"765","tag":"0","syscode":"C20200109164638224","outtime":"2020-01-0916:46:21","corpcode":"A01","supplierID":"13","supplier":"货主S1","suppliercode":"S1","whID":"107","outtype":"5","remarks":""}]
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
         * outID : 2501
         * clientID : 66
         * client : 1212
         * linkman : 111
         * handset : 111
         * outcode : 765
         * tag : 0
         * syscode : C20200109164638224
         * outtime : 2020-01-0916:46:21
         * corpcode : A01
         * supplierID : 13
         * supplier : 货主S1
         * suppliercode : S1
         * whID : 107
         * outtype : 5
         * remarks :
         */

        private String outID;
        private String clientID;
        private String client;
        private String linkman;
        private String handset;
        private String outcode;
        private String tag;
        private String syscode;
        private String outtime;
        private String corpcode;
        private String supplierID;
        private String supplier;
        private String suppliercode;
        private String whID;
        private String outtype;
        private String remarks;

        public String getOutID() {
            return outID;
        }

        public void setOutID(String outID) {
            this.outID = outID;
        }

        public String getClientID() {
            return clientID;
        }

        public void setClientID(String clientID) {
            this.clientID = clientID;
        }

        public String getClient() {
            return client;
        }

        public void setClient(String client) {
            this.client = client;
        }

        public String getLinkman() {
            return linkman;
        }

        public void setLinkman(String linkman) {
            this.linkman = linkman;
        }

        public String getHandset() {
            return handset;
        }

        public void setHandset(String handset) {
            this.handset = handset;
        }

        public String getOutcode() {
            return outcode;
        }

        public void setOutcode(String outcode) {
            this.outcode = outcode;
        }

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getSyscode() {
            return syscode;
        }

        public void setSyscode(String syscode) {
            this.syscode = syscode;
        }

        public String getOuttime() {
            return outtime;
        }

        public void setOuttime(String outtime) {
            this.outtime = outtime;
        }

        public String getCorpcode() {
            return corpcode;
        }

        public void setCorpcode(String corpcode) {
            this.corpcode = corpcode;
        }

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
        }

        public String getSupplier() {
            return supplier;
        }

        public void setSupplier(String supplier) {
            this.supplier = supplier;
        }

        public String getSuppliercode() {
            return suppliercode;
        }

        public void setSuppliercode(String suppliercode) {
            this.suppliercode = suppliercode;
        }

        public String getWhID() {
            return whID;
        }

        public void setWhID(String whID) {
            this.whID = whID;
        }

        public String getOuttype() {
            return outtype;
        }

        public void setOuttype(String outtype) {
            this.outtype = outtype;
        }

        public String getRemarks() {
            return remarks;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
    }
}
