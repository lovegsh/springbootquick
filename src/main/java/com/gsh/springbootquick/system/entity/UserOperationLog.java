package com.gsh.springbootquick.system.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import java.io.Serializable;

/**
 * (UserOperationLog)表实体类
 *
 * @author RestfulToolkitXCode
 * @since 2022-07-06 11:16:07
 */
@Data
@SuppressWarnings("serial")
public class UserOperationLog extends Model<UserOperationLog> {
    
    private Integer id;
    
    private String userId;
    
    private String ip;
    
    private String opData;
    
    private String attr1;
    
    private String attr2;
    
    private String attr3;
    
    private String attr4;
    
    private String attr5;
    
    private String attr6;
    
    private String attr7;
    
    private String attr8;
    
    private String attr9;
    
    private String attr10;
    
    private String attr11;
    
    private String attr12;

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    public Serializable pkVal() {
        return this.id;
    }
    }

