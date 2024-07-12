package com.gsh.springbootquick.system.dao;

import com.gsh.springbootquick.system.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author GSH
 * @create 2023/2/3 11:06
 */
//public interface UserRepository extends GenericJpaRepository<UserEntity, Long> {
@Repository
public interface UserDao extends BaseDao<User> {

    User findByUsername(String username);

    //    @TemplateQuery
    //    UserEntity selectById(@Param("id") Integer id);
    //
    //    UserEntity findAllById(Integer id);
    //
    //    @TemplateQuery
    //    UserDTO selectDtoById(@Param("id") Integer id);
    //
    //    @TemplateQuery
    //    UserDTO selectDtoById2(@Param("id") Integer id);
    //
    //    @TemplateQuery
    //    UserDTO selectDtoById3(@Param("id") Integer id);

}
