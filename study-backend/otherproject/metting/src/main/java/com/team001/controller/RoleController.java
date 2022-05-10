package com.team001.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.CopyUtils;
import com.team001.common.lang.Result;
import com.team001.entity.Role;
import com.team001.entity.UserRole;
import com.team001.service.RoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    /**
     * @param currentPage
     * @param pageSize
     */
    @ApiOperation("角色列表")
    @GetMapping("/list")
    public Result getMeetById(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize) {

        Page<Role> page = new Page<>(currentPage, pageSize);

        try {
            IPage<Role> roleIPage = roleService.page(page, new QueryWrapper<Role>());
            return Result.success(roleIPage);
        } catch (Exception e){
            throw new RuntimeException("获取数据失败");
        }
    }

    @ApiOperation("角色添加或更新")
    @PostMapping("/add")
    @Transactional
    public Result save(@Validated @RequestBody Role role){

        Role temp = null;
        /*id存在表示更新会议室*/
        if(role.getId()!=null){
            temp = roleService.getById(role.getId());
        } else {
            temp = new Role();
        }
        CopyUtils.copyProperties(role,temp);
        boolean b = roleService.saveOrUpdate(temp);
        if(b){
            return Result.success(0,"保存成功",null);
        } else {
            return Result.fail(-1,"保存失败",null);
        }

    }
}
