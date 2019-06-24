package com.itheima.ssm.dao;

import com.itheima.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户持久层接口
 */
public interface UserDao {
    //根据用户名查询用户
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.RoleDao.findByUserId"))
    })
    UserInfo findByUserName(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    //新增用户
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    //根据id查询用户
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class, many = @Many(select = "com.itheima.ssm.dao.RoleDao.findByUserId"))
    })
    UserInfo findById(String id);

    //给用户添加角色
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId) throws Exception;


    //删除用户绑定的角色
    @Delete("delete from users_role where userId=#{userId} and roleId=#{id}")
    void deleteRoleFromUser(@Param("userId") String userId, @Param("id") String id) throws Exception;

    //查询单个用户
    @Select("select * from users where id = #{id}")
    UserInfo findOneById(String id) throws Exception;

    //修改用户信息
    @Update("update users set email=#{email}, username=#{username}, phoneNum=#{phoneNum},status=#{status} where id = #{id}")
    void update(UserInfo userInfo) throws Exception;

    //根据用户id删除关联表信息
    @Delete("delete from users_role where userId = #{id}")
    void deleteUserAndRoleByUserid(String id) throws Exception;

    //根据id删除用户
    @Delete("delete from users where id = #{id}")
    void deleteSelete(String id) throws Exception;
}
