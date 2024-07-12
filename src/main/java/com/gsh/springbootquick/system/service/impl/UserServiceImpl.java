package com.gsh.springbootquick.system.service.impl;

import com.gsh.springbootquick.common.exception.DefaultException;
import com.gsh.springbootquick.system.entity.User;
import com.gsh.springbootquick.system.dao.UserDao;
import com.gsh.springbootquick.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author GSH
 * @create 2023/2/3 14:56
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserDao, User> implements UserService {

    @Resource UserDao userDao;
    @Resource BCryptPasswordEncoder encoder;
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public void save(User entity) {
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        entity.setPassword(encoder.encode(entity.getPassword()));
        super.save(entity);
    }

    @Override
    public void login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        try {
            // 使用spring security的认证方法
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            throw new DefaultException("===== username or password wrong! =====", e);
        }

    }


    @Override
    public List<User> findUsers(){
        return userDao.findAll();
    }


    //    @Override
//    public UserEntity findAllById(Integer id) {
//        return userDao.selectById(id);
//    }
//
//    @Override
//    public UserEntity findAllById2(Integer id) {
//        return userDao.findAllById(id);
//    }
//
//    @Override
//    public UserDTO selectDtoById(Integer id) {
//        return userDao.selectDtoById(id);
//    }
//
//    @Override
//    public UserDTO selectDtoById2(Integer id) {
//        return userDao.selectDtoById2(id);
//    }
//
//    @Override
//    public UserDTO selectDtoById3(Integer id) {
//        return userDao.selectDtoById3(id);
//    }
}
