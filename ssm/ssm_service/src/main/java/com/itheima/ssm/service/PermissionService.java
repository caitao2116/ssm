package com.itheima.ssm.service;

import com.itheima.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll() throws Exception;

    void save(Permission permission) throws Exception;


    List<Permission> findByRoleId(String id) throws Exception;

    Permission findById(String id) throws Exception;

    void update(Permission permission) throws Exception;
}
