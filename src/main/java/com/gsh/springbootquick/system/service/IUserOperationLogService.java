package com.gsh.springbootquick.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gsh.springbootquick.system.entity.UserOperationLog;

import java.util.List;

/**
 * (UserOperationLog)表服务接口
 *
 * @author RestfulToolkitXCode
 * @since 2022-07-06 11:16:07
 */
public interface IUserOperationLogService extends IService<UserOperationLog> {

    List<UserOperationLog> selectAllByIds(List<Integer> ids);
    List<UserOperationLog> selectAllByIds2(List<Integer> ids);
}

