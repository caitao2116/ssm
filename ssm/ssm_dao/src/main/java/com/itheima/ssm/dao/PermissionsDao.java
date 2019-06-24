package com.itheima.ssm.dao;

import com.itheima.ssm.domain.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PermissionsDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findByRoleId(String id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission) throws Exception;

    //根据角色id查询可添加的权限
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findUseableByRoleId(String id);

    //根据id查询权限信息
    @Select("select * from permission where id = #{id}")
    Permission findById(String id) throws Exception;

    //修改权限信息
    @Update("update permission set permissionName = #{permissionName},url=#{url} where id=#{id}")
    void update(Permission permission) throws Exception;
}
