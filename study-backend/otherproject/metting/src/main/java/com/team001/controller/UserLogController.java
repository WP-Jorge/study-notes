package com.team001.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.lang.Result;
import com.team001.entity.User;
import com.team001.entity.UserLog;
import com.team001.service.UserLogService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/user-log")
public class UserLogController {

    @Autowired
    UserLogService userLogService;

    /**
     *
     * @param currentPage 当前页
     * @param pageSize 页码大小
     * @return Result
     */
    @ApiOperation("用户登陆日志列表")
    @GetMapping("/list")
    public Result getMeetById(@RequestParam(defaultValue = "1") Integer currentPage,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String logContent){

        Page<UserLog> page = new Page<>(currentPage,pageSize);

        IPage<UserLog> logIPage = userLogService.page(page, new QueryWrapper<UserLog>().like("logContent",logContent));
        return Result.success(logIPage);
    }
}
