package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/1/9.
 */
public class BuListResult {
    /**
     * status : 200
     * msg : 查询成功
     * data : [{"buID":"60","bucode":"B202001081352002","emID":"176","mover":"bu1","movetime":"2020-01-0813:52:38","isdone":"0","whID":"107","id":"236","wid":"7733","waID":"7426","warearea":"107-G-3-1","goods":"侧后2","gnum":"192","cgID":"1547","supplierID":"13","Cbatch":"201912251322","towarearea":"107-DDD-4-4","supmax":"384","towaid":"12524","movenum":"192","goodscoding":"WW2","supplier":"货主S1","suppliercode":"S1","reason":"地面拣货区补货"},{"buID":"60","bucode":"B202001081352002","emID":"176","mover":"bu1","movetime":"2020-01-0813:52:38","isdone":"0","whID":"107","id":"235","wid":"7734","waID":"7419","warearea":"107-G-1-2","goods":"侧后2","gnum":"108","cgID":"1547","supplierID":"13","Cbatch":"201912251322","towarearea":"107-DDD-4-4","supmax":"384","towaid":"12524","movenum":"108","goodscoding":"WW2","supplier":"货主S1","suppliercode":"S1","reason":"地面拣货区补货"}]
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
         * buID : 60
         * bucode : B202001081352002
         * emID : 176
         * mover : bu1
         * movetime : 2020-01-0813:52:38
         * isdone : 0
         * whID : 107
         * id : 236
         * wid : 7733
         * waID : 7426
         * warearea : 107-G-3-1
         * goods : 侧后2
         * gnum : 192
         * cgID : 1547
         * supplierID : 13
         * Cbatch : 201912251322
         * towarearea : 107-DDD-4-4
         * supmax : 384
         * towaid : 12524
         * movenum : 192
         * goodscoding : WW2
         * supplier : 货主S1
         * suppliercode : S1
         * reason : 地面拣货区补货
         */

        private String buID;
        private String bucode;
        private String emID;
        private String mover;
        private String movetime;
        private String isdone;
        private String whID;
        private String id;
        private String wid;
        private String waID;
        private String warearea;
        private String goods;
        private String gnum;
        private String cgID;
        private String supplierID;
        private String Cbatch;
        private String towarearea;
        private String supmax;
        private String towaid;
        private String movenum;
        private String goodscoding;
        private String supplier;
        private String suppliercode;
        private String reason;

        public String getBuID() {
            return buID;
        }

        public void setBuID(String buID) {
            this.buID = buID;
        }

        public String getBucode() {
            return bucode;
        }

        public void setBucode(String bucode) {
            this.bucode = bucode;
        }

        public String getEmID() {
            return emID;
        }

        public void setEmID(String emID) {
            this.emID = emID;
        }

        public String getMover() {
            return mover;
        }

        public void setMover(String mover) {
            this.mover = mover;
        }

        public String getMovetime() {
            return movetime;
        }

        public void setMovetime(String movetime) {
            this.movetime = movetime;
        }

        public String getIsdone() {
            return isdone;
        }

        public void setIsdone(String isdone) {
            this.isdone = isdone;
        }

        public String getWhID() {
            return whID;
        }

        public void setWhID(String whID) {
            this.whID = whID;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getWid() {
            return wid;
        }

        public void setWid(String wid) {
            this.wid = wid;
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

        public String getTowarearea() {
            return towarearea;
        }

        public void setTowarearea(String towarearea) {
            this.towarearea = towarearea;
        }

        public String getSupmax() {
            return supmax;
        }

        public void setSupmax(String supmax) {
            this.supmax = supmax;
        }

        public String getTowaid() {
            return towaid;
        }

        public void setTowaid(String towaid) {
            this.towaid = towaid;
        }

        public String getMovenum() {
            return movenum;
        }

        public void setMovenum(String movenum) {
            this.movenum = movenum;
        }

        public String getGoodscoding() {
            return goodscoding;
        }

        public void setGoodscoding(String goodscoding) {
            this.goodscoding = goodscoding;
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

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}
