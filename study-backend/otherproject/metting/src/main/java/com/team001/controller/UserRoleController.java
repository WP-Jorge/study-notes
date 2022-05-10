package com.team001.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team001.common.lang.Result;
import com.team001.entity.User;
import com.team001.entity.UserRole;
import com.team001.service.RoleService;
import com.team001.service.UserRoleService;
import com.team001.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/user-role")
public class UserRoleController {

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @ApiOperation("用户权限查询")
    @GetMapping("/get/{id}")
    public Result getUserRole(@PathVariable(value = "id") Integer id){

        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userService.getById(id).getUsername());
        List<UserRole> list = userRoleService.list(queryWrapper);
        return Result.success(0,"查询成功",list);
    }

    @ApiOperation("用户身份添加")
    @PostMapping("/addrole/{id}")
    @Transactional
    public Result addrole(@PathVariable("id") Integer id,@RequestBody JSONObject role ) throws SQLException {

        User user = userService.getById(id);

//        JSONObject jsonObject = JSONArray.parseObject(role);
        JSONArray jsonArray = role.getJSONArray("role");

        //判断角色是否重复
        List<String> rolesByUserName = roleService.getRolesByUserName(user.getUsername());

        boolean flag = false; //重复标志
        for (Object o : jsonArray) {
            for (String s : rolesByUserName) {
                if(s.equals(o.toString())){
                    //重复设置为true
                    flag = true;
                }
            }
            if(!flag){
                UserRole userRole = new UserRole();
                userRole.setRoleName(o.toString());
                userRole.setUsername(user.getUsername());
                try {
                    boolean b = userRoleService.saveOrUpdate(userRole);
                } catch (Exception e){
                    throw new SQLException(e.getMessage());
                }
                //重新开始标志
                flag = false;
            }
        }
        return Result.success("保存成功");
    }

}
