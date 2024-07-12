package com.gsh.springbootquick.system.controller;

import com.gsh.springbootquick.system.entity.Result;
import com.gsh.springbootquick.system.entity.Permission;
import com.gsh.springbootquick.system.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/permission")
@Slf4j
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @GetMapping("/{id}")
    Result<Permission> getById(@PathVariable Long id) {
        return Result.ok(permissionService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return Result.ok();
    }

    @PutMapping("/update")
    Result<Void> update(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    Result<Void> deleteById(@PathVariable Long id) {
        permissionService.deleteById(id);
        return Result.ok();
    }


}
