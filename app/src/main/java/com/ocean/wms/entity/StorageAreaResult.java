package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/17.
 */
public class StorageAreaResult {

    /**
     * status : 200
     * msg : 查询成功
     * data : [{"id":"7899","waID":"7454","warearea":"107-DJ-1-1","whID":"107","goods":"A1","gnum":"50","cgID":"1546","supplierID":"13","Cbatch":"202001140951","ismove":"0","model":null,"hu":"R2020011409521610015461","istype":"1","isok":"0"},{"id":"7900","waID":"7454","warearea":"107-DJ-1-1","whID":"107","goods":"A1","gnum":"50","cgID":"1546","supplierID":"13","Cbatch":"202001140951","ismove":"0","model":null,"hu":"R2020011409521610015462","istype":"1","isok":"0"}]
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
         * id : 7899
         * waID : 7454
         * warearea : 107-DJ-1-1
         * whID : 107
         * goods : A1
         * gnum : 50
         * cgID : 1546
         * supplierID : 13
         * Cbatch : 202001140951
         * ismove : 0
         * model : null
         * hu : R2020011409521610015461
         * istype : 1
         * isok : 0
         */

        private String id;
        private String waID;
        private String warearea;
        private String whID;
        private String goods;
        private String gnum;
        private String cgID;
        private String supplierID;
        private String Cbatch;
        private String ismove;
        private Object model;
        private String hu;
        private String istype;
        private String isok;

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

        public String getWhID() {
            return whID;
        }

        public void setWhID(String whID) {
            this.whID = whID;
        }

        public String getGoods() {
            return goods;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getGnum() {
            return gnum;
        }

        public void setGnum(String gnum) {
            this.gnum = gnum;
        }

        public String getCgID() {
            return cgID;
        }

        public void setCgID(String cgID) {
            this.cgID = cgID;
        }

        public String getSupplierID() {
            return supplierID;
        }

        public void setSupplierID(String supplierID) {
            this.supplierID = supplierID;
        }

        public String getCbatch() {
            return Cbatch;
        }

        public void setCbatch(String Cbatch) {
            this.Cbatch = Cbatch;
        }

        public String getIsmove() {
            return ismove;
        }

        public void setIsmove(String ismove) {
            this.ismove = ismove;
        }

        public Object getModel() {
            return model;
        }

        public void setModel(Object model) {
            this.model = model;
        }

        public String getHu() {
            return hu;
        }

        public void setHu(String hu) {
            this.hu = hu;
        }

        public String getIstype() {
            return istype;
        }

        public void setIstype(String istype) {
            this.istype = istype;
        }

        public String getIsok() {
            return isok;
        }

        public void setIsok(String isok) {
            this.isok = isok;
        }
    }
}
