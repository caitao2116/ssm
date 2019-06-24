package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.RoleDao;
import com.itheima.ssm.domain.Role;
import com.itheima.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    //查询所有角色
    @Override
    public List<Role> findAll() throws Exception {
        return roleDao.findAll();
    }

    //新建角色
    @Override
    public void save(Role role) throws Exception {
        roleDao.save(role);
    }

    @Override
    public List<Role> findByUserId(String id) throws Exception {
        return roleDao.findUserableByUserId(id);
    }

    //根据id查询角色
    @Override
    public Role findId(String id) throws Exception {
        return roleDao.findById(id);
    }

    //给角色天界权限
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    //根据id查询可删除的角色
    @Override
    public List<Role> findRoleByUserId(String id) throws Exception {
        return roleDao.findRoleByUserId(id);
    }
}
