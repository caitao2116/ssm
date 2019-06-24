package com.itheima.ssm.service;

import com.itheima.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll() throws Exception;


    void save(Role role) throws Exception;

    List<Role> findByUserId(String id) throws Exception;


    Role findId(String id) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    List<Role> findRoleByUserId(String id) throws Exception;
}
