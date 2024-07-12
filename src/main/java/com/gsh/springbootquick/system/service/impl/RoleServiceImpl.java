package com.gsh.springbootquick.system.service.impl;

import com.gsh.springbootquick.system.dao.RoleDao;
import com.gsh.springbootquick.system.entity.Role;
import com.gsh.springbootquick.system.service.RoleService;
import com.gsh.springbootquick.system.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleDao, Role> implements RoleService {



}
