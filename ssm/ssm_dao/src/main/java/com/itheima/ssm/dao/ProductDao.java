package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 产品持久层接口
 */
public interface ProductDao {

    /**
     * 查询所有产品信息
     * @return
     */
    @Select("select * from product")
    List<Product> findAll();
}
