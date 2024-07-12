package com.gsh.springbootquick.system.service;

import com.gsh.springbootquick.system.entity.BaseEntity;

/**
 * @author GSH
 * @create 2023/3/7 9:37
 */
public interface BaseService<T extends BaseEntity> {

    T findById(Long id);

    void save(T entity);

    void updateById(T entity);

    void deleteById(Long id);
}
