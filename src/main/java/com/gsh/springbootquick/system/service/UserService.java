package com.gsh.springbootquick.system.service;

import com.gsh.springbootquick.system.entity.User;

import java.util.List;

/**
 * @author GSH
 * @create 2023/2/4 13:33
 */
public interface UserService extends BaseService<User> {

    void login(User user);

    List<User> findUsers();

//    UserEntity findAllById(Integer id);
//
//    UserEntity findAllById2(Integer id);
//
//    UserDTO selectDtoById(Integer id);
//
//    UserDTO selectDtoById2(Integer id);
//
//    UserDTO selectDtoById3(Integer id);
}