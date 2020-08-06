package com.ocean.wms.tools;

/**
 * Created by James on 2020/5/6.
 */
public class Util {


    //获取当前10位数时间戳
    public static long getTimeStamp(){
        long timeStamp = System.currentTimeMillis();
        String str = String.valueOf(timeStamp);
        String strh = str.substring(0,str.length()-3);   //截取
        return Long.decode(strh);
    }


}
