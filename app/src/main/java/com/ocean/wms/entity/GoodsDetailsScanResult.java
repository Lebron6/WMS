package com.ocean.wms.entity;

/**
 * Created by James on 2020/1/16.
 */
public class GoodsDetailsScanResult {
    /**
     * invoice : R2020011014183828
     * supplierID : 66
     * corpcode : 2
     * cgID : 1585
     * goodscoding : S11A11027AA
     * Cbatch : 202001101418
     * kk : 50
     * HU : R202001101418382815851
     * plID : 5270
     */

    private String invoice;
    private String supplierID;
    private String corpcode;
    private String cgID;
    private String goodscoding;
    private String Cbatch;
    private String kk;
    private String HU;
    private String plID;

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getCorpcode() {
        return corpcode;
    }

    public void setCorpcode(String corpcode) {
        this.corpcode = corpcode;
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

    public String getCbatch() {
        return Cbatch;
    }

    public void setCbatch(String Cbatch) {
        this.Cbatch = Cbatch;
    }

    public String getKk() {
        return kk;
    }

    public void setKk(String kk) {
        this.kk = kk;
    }

    public String getHU() {
        return HU;
    }

    public void setHU(String HU) {
        this.HU = HU;
    }

    public String getPlID() {
        return plID;
    }

    public void setPlID(String plID) {
        this.plID = plID;
    }
}
