package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.UserDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.domain.UserInfo;
import com.itheima.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据username查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUserName(username);

        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false:true,true,true,true,getAuthenty(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthenty(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority( "ROLE_"+role.getRoleName()));
        }
        return list;
    }

    /**
     * 查询所有用户信息
     * @return
     * @throws Exception
     */
    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    //保存新增用户
    @Override
    public void save(UserInfo userInfo) throws Exception {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //根据id查询用户
    @Override
    public UserInfo findById(String id) throws Exception {
        return userDao.findById(id);
    }

    //给用户添加角色
    @Override
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId,roleId);
        }
    }

    //删除用户绑定的角色
    @Override
    public void deleteRoleFromUser(String userId, String[] ids) throws Exception {
        for (String id : ids) {
            userDao.deleteRoleFromUser(userId,id);
        }
    }

    //查询单个用户信息
    @Override
    public UserInfo findOneById(String id) throws Exception {
        return userDao.findOneById(id);
    }

    //修改用户信息
    @Override
    public void update(UserInfo userInfo) throws Exception {
        userDao.update(userInfo);
    }

    //删除选中用户
    @Override
    public void deleteSelected(String[] ids) throws Exception {
        for (String id : ids) {
            userDao.deleteUserAndRoleByUserid(id);
            userDao.deleteSelete(id);
        }
    }
}
