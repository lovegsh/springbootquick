package com.gsh.springbootquick.system.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gsh.springbootquick.system.entity.UserOperationLog;
import com.gsh.springbootquick.system.service.IUserOperationLogService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (UserOperationLog)表控制层
 *
 * @author RestfulToolkitXCode
 * @since 2022-07-06 11:15:48
 */
@RestController
@RequestMapping("userOperationLog")
public class UserOperationLogController extends ApiController {
    /**
     * 服务对象
     */
    @Resource
    private IUserOperationLogService userOperationLogService;

    @GetMapping("/ids")
    public R selectAllByIds(@RequestParam String ids){
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(s->Integer.parseInt(s.trim())).collect(Collectors.toList());
        return success(userOperationLogService.selectAllByIds(list));
    }

    @GetMapping("/ids2")
    public R selectAllByIds2(@RequestParam String ids){
        List<Integer> list = Arrays.stream(ids.split(","))
                .map(s->Integer.parseInt(s.trim())).collect(Collectors.toList());
        return success(userOperationLogService.selectAllByIds2(list));
    }

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param userOperationLog 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Page<UserOperationLog> page, UserOperationLog userOperationLog) {
        page.setCurrent(2);
        page.setSize(5);
        page.setTotal(20);
        return success(this.userOperationLogService.page(page, new QueryWrapper<>(userOperationLog)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return success(this.userOperationLogService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param userOperationLog 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody UserOperationLog userOperationLog) {
        return success(this.userOperationLogService.save(userOperationLog));
    }

    /**
     * 修改数据
     *
     * @param userOperationLog 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody UserOperationLog userOperationLog) {
        return success(this.userOperationLogService.updateById(userOperationLog));
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        return success(this.userOperationLogService.removeByIds(idList));
    }
}

