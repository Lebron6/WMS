package com.ocean.wms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 2020/5/6.
 */
public class SearchCarInfo implements Serializable {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"sum":"1","list":[{"id":"13","carID":"157","whID":"109","clinet":"82,71","inputtime":"2020-04-2411:14:39","endtime":"","status":"0","carnumber":"桂B-A2338","carmodel":"9.6","carowner":"","ownermobile":"","driver":"司机1号","handset":"18424848","status_name":"运行中"}]}
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

    public static class DataBean implements Serializable{
        /**
         * sum : 1
         * list : [{"id":"13","carID":"157","whID":"109","clinet":"82,71","inputtime":"2020-04-2411:14:39","endtime":"","status":"0","carnumber":"桂B-A2338","carmodel":"9.6","carowner":"","ownermobile":"","driver":"司机1号","handset":"18424848","status_name":"运行中"}]
         */

        private String sum;
        private List<ListBean> list;

        public String getSum() {
            return sum;
        }

        public void setSum(String sum) {
            this.sum = sum;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean implements Serializable{
            /**
             * id : 13
             * carID : 157
             * whID : 109
             * clinet : 82,71
             * inputtime : 2020-04-2411:14:39
             * endtime :
             * status : 0
             * carnumber : 桂B-A2338
             * carmodel : 9.6
             * carowner :
             * ownermobile :
             * driver : 司机1号
             * handset : 18424848
             * status_name : 运行中
             */

            private String id;
            private String carID;
            private String whID;
            private String clinet;
            private String inputtime;
            private String endtime;
            private String status;
            private String carnumber;
            private String carmodel;
            private String carowner;
            private String ownermobile;
            private String driver;
            private String handset;
            private String status_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCarID() {
                return carID;
            }

            public void setCarID(String carID) {
                this.carID = carID;
            }

            public String getWhID() {
                return whID;
            }

            public void setWhID(String whID) {
                this.whID = whID;
            }

            public String getClinet() {
                return clinet;
            }

            public void setClinet(String clinet) {
                this.clinet = clinet;
            }

            public String getInputtime() {
                return inputtime;
            }

            public void setInputtime(String inputtime) {
                this.inputtime = inputtime;
            }

            public String getEndtime() {
                return endtime;
            }

            public void setEndtime(String endtime) {
                this.endtime = endtime;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getCarnumber() {
                return carnumber;
            }

            public void setCarnumber(String carnumber) {
                this.carnumber = carnumber;
            }

            public String getCarmodel() {
                return carmodel;
            }

            public void setCarmodel(String carmodel) {
                this.carmodel = carmodel;
            }

            public String getCarowner() {
                return carowner;
            }

            public void setCarowner(String carowner) {
                this.carowner = carowner;
            }

            public String getOwnermobile() {
                return ownermobile;
            }

            public void setOwnermobile(String ownermobile) {
                this.ownermobile = ownermobile;
            }

            public String getDriver() {
                return driver;
            }

            public void setDriver(String driver) {
                this.driver = driver;
            }

            public String getHandset() {
                return handset;
            }

            public void setHandset(String handset) {
                this.handset = handset;
            }

            public String getStatus_name() {
                return status_name;
            }

            public void setStatus_name(String status_name) {
                this.status_name = status_name;
            }
        }
    }
}
