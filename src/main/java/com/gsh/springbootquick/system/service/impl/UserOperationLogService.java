package com.gsh.springbootquick.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gsh.springbootquick.system.mapper.UserOperationLogMapper;
import com.gsh.springbootquick.system.entity.UserOperationLog;
import com.gsh.springbootquick.system.service.IUserOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * (UserOperationLog)表服务实现类
 *
 * @author RestfulToolkitXCode
 * @since 2022-07-06 11:16:08
 */
@Service("userOperationLogService")
public class UserOperationLogService extends ServiceImpl<UserOperationLogMapper, UserOperationLog> implements IUserOperationLogService {

    @Override
    public List<UserOperationLog> selectAllByIds(List<Integer> ids) {
        return baseMapper.selectAllByIds(ids);
    }

    @Override
    public List<UserOperationLog> selectAllByIds2(List<Integer> ids) {
        return baseMapper.selectAllByIds2(ids);
    }
}

