package com.ocean.wms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by James on 2020/4/29.
 */
public class QueryCarResult  implements Serializable {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"car_type":0,"client_list":[{"clientID":"70","corpcode":"BJ001","corp":"宝骏总装","corporation":"宝骏基地整车工厂总装","address":"柳州鱼峰区"},{"clientID":"71","corpcode":"WL001","corp":"五菱总装","corporation":"上汽通用五菱柳州基地","address":"柳州市区"}],"car_list":[]}
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
         * car_type : 0
         * client_list : [{"clientID":"70","corpcode":"BJ001","corp":"宝骏总装","corporation":"宝骏基地整车工厂总装","address":"柳州鱼峰区"},{"clientID":"71","corpcode":"WL001","corp":"五菱总装","corporation":"上汽通用五菱柳州基地","address":"柳州市区"}]
         * car_list : []
         */

        private int car_type;
        private String car_num;

        public String getCar_num() {
            return car_num;
        }

        public void setCar_num(String car_num) {
            this.car_num = car_num;
        }

        private List<ClientListBean> client_list;
        private CarListBean car_list;

        public CarListBean getCar_list() {
            return car_list;
        }

        public void setCar_list(CarListBean car_list) {
            this.car_list = car_list;
        }

        public int getCar_type() {
            return car_type;
        }

        public void setCar_type(int car_type) {
            this.car_type = car_type;
        }

        public List<ClientListBean> getClient_list() {
            return client_list;
        }

        public void setClient_list(List<ClientListBean> client_list) {
            this.client_list = client_list;
        }

        public static class ClientListBean implements Serializable{
            /**
             * clientID : 70
             * corpcode : BJ001
             * corp : 宝骏总装
             * corporation : 宝骏基地整车工厂总装
             * address : 柳州鱼峰区
             */

            private String clientID;
            private String corpcode;
            private String corp;
            private String corporation;
            private String address;
            private int tag=0;  //0未选中 1选中

            public int getTag() {
                return tag;
            }

            public void setTag(int tag) {
                this.tag = tag;
            }

            public String getClientID() {
                return clientID;
            }

            public void setClientID(String clientID) {
                this.clientID = clientID;
            }

            public String getCorpcode() {
                return corpcode;
            }

            public void setCorpcode(String corpcode) {
                this.corpcode = corpcode;
            }

            public String getCorp() {
                return corp;
            }

            public void setCorp(String corp) {
                this.corp = corp;
            }

            public String getCorporation() {
                return corporation;
            }

            public void setCorporation(String corporation) {
                this.corporation = corporation;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
        public static class CarListBean   implements Serializable {
            /**
             * id : 11
             * carID : 158
             * whID : 109
             * clinet : 82, 83, 84
             * inputtime : 2020-04-30 16:04:08
             * endtime :
             * status : 0
             * carinfo : ["柳州库","青岛库","重庆库"]
             * duration : 1588233848
             */

            private String id;
            private String carID;
            private String whID;
            private String clinet;
            private String inputtime;
            private String endtime;
            private String status;
            private Long duration;
            private List<String> carinfo;

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

            public Long getDuration() {
                return duration;
            }

            public void setDuration(Long duration) {
                this.duration = duration;
            }

            public List<String> getCarinfo() {
                return carinfo;
            }

            public void setCarinfo(List<String> carinfo) {
                this.carinfo = carinfo;
            }
        }
    }
}
