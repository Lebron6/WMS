package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/10.
 */
public class OutGoodsDetails {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"outID":"2501","clientID":"66","client":"1212","linkman":"111","handset":"111","outcode":"765","tag":"0","syscode":"C20200109164638224","outtime":"2020-01-0916:46:21","corpcode":"A01","supplierID":"13","supplier":"货主S1","suppliercode":"S1","whID":"107","outtype":"5","remarks":"","list":[{"id":"3523","outID":"2501","outcode":"765","cgID":"1546","goodscoding":"A1","goods":"测试","outnum":"10","model":"MD","supplier":"货主S1","supplierID":"13","suppliercode":"S1","whID":"107","tag":"0","alreadyoff":"0"},{"id":"3524","outID":"2501","outcode":"765","cgID":"1547","goodscoding":"WW2","goods":"侧后2","outnum":"64","model":"","supplier":"货主S1","supplierID":"13","suppliercode":"S1","whID":"107","tag":"0","alreadyoff":"0"}]}
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
         * list : [{"id":"3523","outID":"2501","outcode":"765","cgID":"1546","goodscoding":"A1","goods":"测试","outnum":"10","model":"MD","supplier":"货主S1","supplierID":"13","suppliercode":"S1","whID":"107","tag":"0","alreadyoff":"0"},{"id":"3524","outID":"2501","outcode":"765","cgID":"1547","goodscoding":"WW2","goods":"侧后2","outnum":"64","model":"","supplier":"货主S1","supplierID":"13","suppliercode":"S1","whID":"107","tag":"0","alreadyoff":"0"}]
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
        private List<ListBean> list;

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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * id : 3523
             * outID : 2501
             * outcode : 765
             * cgID : 1546
             * goodscoding : A1
             * goods : 测试
             * outnum : 10
             * model : MD
             * supplier : 货主S1
             * supplierID : 13
             * suppliercode : S1
             * whID : 107
             * tag : 0
             * alreadyoff : 0
             */

            private String id;
            private String outID;
            private String outcode;
            private String cgID;
            private String goodscoding;
            private String goods;
            private String outnum;
            private String model;
            private String supplier;
            private String supplierID;
            private String suppliercode;
            private String whID;
            private String tag;
            private String alreadyoff;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getOutID() {
                return outID;
            }

            public void setOutID(String outID) {
                this.outID = outID;
            }

            public String getOutcode() {
                return outcode;
            }

            public void setOutcode(String outcode) {
                this.outcode = outcode;
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

            public String getOutnum() {
                return outnum;
            }

            public void setOutnum(String outnum) {
                this.outnum = outnum;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSupplier() {
                return supplier;
            }

            public void setSupplier(String supplier) {
                this.supplier = supplier;
            }

            public String getSupplierID() {
                return supplierID;
            }

            public void setSupplierID(String supplierID) {
                this.supplierID = supplierID;
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

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getAlreadyoff() {
                return alreadyoff;
            }

            public void setAlreadyoff(String alreadyoff) {
                this.alreadyoff = alreadyoff;
            }
        }
    }
}
