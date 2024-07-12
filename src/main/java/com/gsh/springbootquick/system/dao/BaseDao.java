package com.gsh.springbootquick.system.dao;

import com.gsh.springbootquick.system.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author GSH
 * @create 2023/3/6 20:18
 */
@NoRepositoryBean
public interface BaseDao<T extends BaseEntity> extends JpaRepository<T, Long> {

    @Override
    @Transactional(readOnly = true)
    @Query("select t from #{#entityName} t where t.isDeleted = 0")
    List<T> findAll();

    @Override
    @Transactional(readOnly = true)
    @Query("select t from #{#entityName} t where t.id = :id and t.isDeleted = 0")
    Optional<T> findById(Long id);

    @Transactional(rollbackFor = Exception.class)
    @Modifying
    @Query("update #{#entityName} t set t.isDeleted = 1 where t.id = :id")
    void logicDeleteById(Long id);

}
