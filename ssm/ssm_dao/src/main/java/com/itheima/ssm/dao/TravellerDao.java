package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TravellerDao {

    //根据订单id查询旅客信息
    @Select("select * from traveller t inner join order_traveller ot on t.id = ot.travellerId where ot.orderId = #{ordersId}")
    List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
