package com.itheima.ssm.service;

import com.itheima.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<UserInfo> findAll() throws Exception;


    void save(UserInfo userInfo) throws Exception;


    UserInfo findById(String id) throws Exception;


    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    void deleteRoleFromUser(String userId, String[] ids) throws Exception;

    UserInfo findOneById(String id) throws Exception;

    void update(UserInfo userInfo) throws Exception;

    void deleteSelected(String[] ids) throws Exception;
}
