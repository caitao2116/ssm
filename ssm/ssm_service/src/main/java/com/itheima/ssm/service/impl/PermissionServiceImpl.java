package com.itheima.ssm.service.impl;

import com.itheima.ssm.dao.PermissionsDao;
import com.itheima.ssm.domain.Permission;
import com.itheima.ssm.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionsDao permissionsDao;

    @Override
    public List<Permission> findAll() throws Exception {
        return permissionsDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {
        permissionsDao.save(permission);
    }

    @Override
    public List<Permission> findByRoleId(String id) throws Exception {
        return permissionsDao.findUseableByRoleId(id);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionsDao.findById(id);
    }

    @Override
    public void update(Permission permission) throws Exception {
        permissionsDao.update(permission);
    }
}
