package com.ocean.wms.api;

import com.ocean.wms.entity.BuDanResult;
import com.ocean.wms.entity.BuListResult;
import com.ocean.wms.entity.Buone;
import com.ocean.wms.entity.CarBackResult;
import com.ocean.wms.entity.Distribution;
import com.ocean.wms.entity.FindScanResult;
import com.ocean.wms.entity.GetChuResult;
import com.ocean.wms.entity.GoodsDetails;
import com.ocean.wms.entity.InventoryListResult;
import com.ocean.wms.entity.QueryCarResult;
import com.ocean.wms.entity.SearchCarInfo;
import com.ocean.wms.entity.SearchResult;
import com.ocean.wms.entity.SendCarResult;
import com.ocean.wms.entity.StorageAreaResult;
import com.ocean.wms.entity.MoveResult;
import com.ocean.wms.entity.OutGoodsDetails;
import com.ocean.wms.entity.OutGoodsList;
import com.ocean.wms.entity.OutGoodsResult;
import com.ocean.wms.entity.UpShelfaResult;
import com.ocean.wms.entity.UserInfo;
import com.ocean.wms.entity.WareScanResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by James on 2020/1/6.
 */
public interface WMSApi {

     /**
     * 用户登录
     * @param userName
     * @param password
     * @return
     */
    @POST("login.html")
    @FormUrlEncoded
    Call<UserInfo> userLogin(@Field("username") String userName, @Field("password") String password);

    /**
     * 自动分配储位
     * @param token
     * @param cgID
     * @return
     */
    @POST("zidistribution.html")
    @FormUrlEncoded
    Call<Distribution> distribution(@Field("token") String token, @Field("cgID") String cgID, @Field("Yunum") String Yunum, @Field("supplierID") String supplierID);

    /**
     * 判断上架储位是否和分配储位相同
     * @param token
     * @return
     */
    @POST("getchu.html")
    @FormUrlEncoded
    Call<GetChuResult> getChu(@Field("token") String token, @Field("warearea") String warearea, @Field("towarearea") String towarearea);

    /**
     * 上架
     * @param token
     * @return
     */
    @POST("upshelfa.html")
    @FormUrlEncoded
    Call<UpShelfaResult> upShelfa(@Field("token") String token, @Field("goodscoding") String goodscoding, @Field("PUGodd") String PUGodd, @Field("warearea") String warearea, @Field("num") String num, @Field("hu") String hu);

    /**
     * 获得单拖目前储位信息
     * @param token
     * @return
     */
    @POST("findscan.html")
    @FormUrlEncoded
    Call<FindScanResult> findScan(@Field("token") String token, @Field("gnum") String gnum, @Field("hu") String hu);

    /**
     * 移库
     * @param token
     * @return
     */
    @POST("movelib.html")
    @FormUrlEncoded
    Call<MoveResult> moveLib(@Field("token") String token, @Field("reason") String reason, @Field("warearea") String warearea,
                             @Field("towarearea") String towarearea, @Field("oldnum") String oldnum,
                             @Field("movenum") String movenum, @Field("cgID") String cgID,
                             @Field("supplierID") String supplierID, @Field("suppliercode") String suppliercode,
                             @Field("waID") String waID,
                             @Field("goodscoding") String goodscoding, @Field("Cbatch") String Cbatch,
                             @Field("hu") String hu, @Field("wid") String wid);

    /**
     * 补货列表
     * @param token
     * @return
     */
    @POST("bulist.html")
    @FormUrlEncoded
    Call<BuListResult> buList(@Field("token") String token);

    /**
     * 补货详情
     * @param token
     * @return
     */
    @POST("getbuone.html")
    @FormUrlEncoded
    Call<Buone> getBuone(@Field("token") String token, @Field("id") String id);

    /**
     * 验证补货单原储位信息
     * @param token
     * @return
     */
    @POST("getbudan.html")
    @FormUrlEncoded
    Call<BuDanResult> getBuDan(@Field("token") String token, @Field("wid") String id, @Field("hu") String hu);

    /**
     * 补货完成
     * @param token
     * @return
     */
    @POST("budone.html")
    @FormUrlEncoded
    Call<BuDanResult> buDone(@Field("token") String token, @Field("id") String id, @Field("hu") String hu);

    /**
     * 出库单列表
     * @param token
     * @return
     */
    @POST("outgoods.html")
    @FormUrlEncoded
    Call<OutGoodsList> outGoodList(@Field("token") String token);

    /**
     * 出库单详情
     * @param token
     * @return
     */
    @POST("outgoodsone.html")
    @FormUrlEncoded
    Call<OutGoodsDetails> outGoodsOne(@Field("token") String token, @Field("outID") String outID);

    /**
     * 出库完成
     * @param token
     * @return
     */
    @POST("chudone.html")
    @FormUrlEncoded
    Call<OutGoodsResult> chuDone(@Field("token") String token, @Field("outID") String outID);

    /**
     * 出库单货物明细
     * @param token
     * @return
     */
    @POST("outhuo.html")
    @FormUrlEncoded
    Call<GoodsDetails> outHuo(@Field("token") String token, @Field("outID") String outID, @Field("id") String id);

    /**
     * 验证出库单货物明细储位信息
     * @param token
     * @return
     */
    @POST("getoutchu.html")
    @FormUrlEncoded
    Call<OutGoodsResult> getOutChu(@Field("token") String token, @Field("outID") String outID, @Field("id") String id, @Field("hu") String hu);

    /**
     * 出库确认
     * @param token
     * @return
     */
    @POST("outsure.html")
    @FormUrlEncoded
    Call<OutGoodsResult> outSure(@Field("token") String token, @Field("outID") String outID, @Field("cgID") String cgID
            , @Field("id") String id, @Field("warearea[]") List<String> warearea, @Field("outnum[]") List<String> outnum,
                                 @Field("Cbatch[]") List<String> Cbatch, @Field("hu[]") List<String> hu,
                                 @Field("wid[]") List<String> wid);

    /**
     * 库存汇总
     * @param token
     * @return
     */
    @POST("Getreserve.html")
    @FormUrlEncoded
    Call<InventoryListResult> getReserve(@Field("token") String token, @Field("goodscoding") String goodscoding, @Field("Cname") String Cname, @Field("supplier") String supplier);

    /**
     * 库存汇总
     * @param token
     * @return
     */
    @POST("showdetails.html")
    @FormUrlEncoded
    Call<StorageAreaResult> showDetails(@Field("token") String token, @Field("cgID") String cgID, @Field("supplierID") String supplierID, @Field("istype") String istype);

    /**
     * 补货详情
     * @param token
     * @return
     */
    @POST("findnow.html")
    @FormUrlEncoded
    Call<SearchResult> search(@Field("token") String token, @Field("hu") String hu);

    /**
     * 扫描二维码显示当前状态
     * @param token
     * @return
     */
    @POST("carnum.html")
    @FormUrlEncoded
    Call<QueryCarResult> carNum(@Field("token") String token, @Field("carID") String carID);

    /**
     * 发车
     * @param token
     * @return
     */
    @POST("facar.html")
    @FormUrlEncoded
    Call<SendCarResult> faCar(@Field("token") String token, @Field("carID") String carID, @Field("clinet") String clinet);

    /**
     * 车辆返回
     * @param token
     * @return
     */
    @POST("endcar.html")
    @FormUrlEncoded
    Call<CarBackResult> back(@Field("token") String token, @Field("carID") String carID);

    /**
     * 搜索车辆
     * @param token
     * @return
     */
    @POST("showcar.html")
    @FormUrlEncoded
    Call<SearchCarInfo> searchCar(@Field("token") String token,
                                  @Field("carnumber") String carnumber,
                                  @Field("driver") String driver,
                                  @Field("starttime") String starttime,
                                  @Field("endtime") String endtime);
}
