package com.gsh.springbootquick.system.service.impl;

import com.gsh.springbootquick.system.dao.UserDao;
import com.gsh.springbootquick.system.entity.Permission;
import com.gsh.springbootquick.system.entity.Role;
import com.gsh.springbootquick.system.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @author GSH
 * @create 2023/3/7 9:48
 * springsecurity 登录会调用 UserDetailsService.loadUserByUsername
 * 为了返回 UserDetails ,user类需要实现 UserDetails 并重写其中一些方法
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        List<Role> roles = user.getRoles();
        List<Permission> permissions = roles.stream().flatMap(role -> role.getPermissions().stream()).distinct().toList();
        user.setAuthorities(permissions);
        return user;
    }
}
