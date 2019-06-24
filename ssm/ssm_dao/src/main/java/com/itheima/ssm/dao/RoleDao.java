package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RoleDao {

    @Select("select * from role where id in(select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id",javaType = java.util.List.class,many = @Many(select = "com.itheima.ssm.dao.PermissionsDao.findByRoleId"))
    })
    List<Role> findByUserId(String userId) throws Exception;


    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role) throws Exception;

    //查询用户可添加的角色
    @Select("select * from role where id not in (select roleId from users_role where userId = #{id})")
    List<Role> findUserableByUserId(String id) throws Exception;

    //根据id查询角色
    @Select("select * from role where id = #{id}")
    Role findById(String id) throws Exception;

    //给角色添加权限
    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId) throws Exception;

    //根据用户id查询已经具有的角色
    @Select("select * from role where id in(select roleId from users_role where userId = #{id})")
    List<Role> findRoleByUserId(String id) throws Exception;
}