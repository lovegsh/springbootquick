package com.gsh.springbootquick.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.gsh.springbootquick.system.entity.UserOperationLog;

import java.util.List;

/**
 * (UserOperationLog)表数据库访问层
 *
 * @author RestfulToolkitXCode
 * @since 2022-07-06 11:16:05
 */
@Mapper
public interface UserOperationLogMapper extends BaseMapper<UserOperationLog> {

  List<UserOperationLog> selectAllByIds(List<Integer> ids);

  List<UserOperationLog> selectAllByIds2(List<Integer> ids);

}
