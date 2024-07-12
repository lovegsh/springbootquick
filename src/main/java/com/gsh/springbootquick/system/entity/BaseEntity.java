package com.gsh.springbootquick.system.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author GSH
 * @create 2023/3/5 16:51
 */
@Data
@MappedSuperclass
public class BaseEntity {

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private Long createdBy;

    /**
     * 创建时间
     */
    @Column(name = "created_time")
    private LocalDateTime createdTime;

    /**
     * 修改人
     */
    @Column(name = "updated_by")
    private Long updatedBy;

    /**
     * 修改时间
     */
    @Column(name = "updated_time")
    private LocalDateTime updatedTime;

    /**
     * 逻辑删除字段 0-未删除 1-已删除
     */
    @Column(name = "is_deleted")
    private Integer isDeleted;

    /**
     * 备注
     */
    @Column
    private String remark;
}

