package com.itheima.ssm.service;

import com.itheima.ssm.domain.Product;

import java.util.List;

/**
 * 产品业务层接口
 */
public interface ProductService {
    //查询所有产品信息
    List<Product> findAll() throws Exception;

    //保存产品信息
    void save(Product product) throws Exception;
}
