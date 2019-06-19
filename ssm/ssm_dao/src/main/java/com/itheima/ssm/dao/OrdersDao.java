package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Member;
import com.itheima.ssm.domain.Orders;
import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单持久层接口
 */
public interface OrdersDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderNum", property = "orderDesc"),
            @Result(column = "productId", property = "product",javaType = Product.class ,one =@One(select = "com.itheima.ssm.dao.ProductDao.findById"))
    })
    List<Orders> findAll() throws Exception;

    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "orderNum", property = "orderNum"),
            @Result(column = "orderTime", property = "orderTime"),
            @Result(column = "orderStatus", property = "orderStatus"),
            @Result(column = "peopleCount", property = "peopleCount"),
            @Result(column = "payType", property = "payType"),
            @Result(column = "orderDesc", property = "orderDesc"),
            @Result(column = "productId", property = "product",javaType = Product.class ,one =@One(select = "com.itheima.ssm.dao.ProductDao.findById")),
            @Result(column = "memberId", property = "member", javaType = Member.class, one = @One(select = "com.itheima.ssm.dao.MemberDao.findById")),
            @Result(column = "id",property = "travellers",javaType =java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.TravellerDao.findByOrdersId"))
    })
    Orders findById(String id) throws Exception;
}
