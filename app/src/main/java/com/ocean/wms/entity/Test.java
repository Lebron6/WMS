package com.ocean.wms.entity;

import java.util.List;

/**
 * Created by James on 2020/5/6.
 */
public class Test {
    /**
     * status : 200
     * msg : 查询成功
     * data : {"car_type":1,"car_num":"桂BK6862","client_list":[],"car_list":{"id":"11","carID":"158","whID":"109","clinet":"82, 83, 84","inputtime":"2020-04-30 16:04:08","endtime":"","status":"0","carinfo":["柳州库","青岛库","重庆库"],"duration":1588233848}}
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
         * car_type : 1
         * car_num : 桂BK6862
         * client_list : []
         * car_list : {"id":"11","carID":"158","whID":"109","clinet":"82, 83, 84","inputtime":"2020-04-30 16:04:08","endtime":"","status":"0","carinfo":["柳州库","青岛库","重庆库"],"duration":1588233848}
         */

        private int car_type;
        private String car_num;
        private CarListBean car_list;
        private List<?> client_list;

        public int getCar_type() {
            return car_type;
        }

        public void setCar_type(int car_type) {
            this.car_type = car_type;
        }

        public String getCar_num() {
            return car_num;
        }

        public void setCar_num(String car_num) {
            this.car_num = car_num;
        }

        public CarListBean getCar_list() {
            return car_list;
        }

        public void setCar_list(CarListBean car_list) {
            this.car_list = car_list;
        }

        public List<?> getClient_list() {
            return client_list;
        }

        public void setClient_list(List<?> client_list) {
            this.client_list = client_list;
        }

        public static class CarListBean {
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
            private int duration;
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

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
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
