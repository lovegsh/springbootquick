package com.gsh.springbootquick.system.controller;

import com.gsh.springbootquick.system.entity.Result;
import com.gsh.springbootquick.system.entity.Role;
import com.gsh.springbootquick.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
@Slf4j
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("/{id}")
    Result<Role> getById(@PathVariable Long id) {
        return Result.ok(roleService.findById(id));
    }

    @PostMapping("/save")
    Result<Void> save(@RequestBody Role role) {
        roleService.save(role);
        return Result.ok();
    }

    @PutMapping("/update")
    Result<Void> update(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.ok();
    }

    @DeleteMapping("/{id}")
    Result<Void> deleteById(@PathVariable Long id) {
        roleService.deleteById(id);
        return Result.ok();
    }


}
