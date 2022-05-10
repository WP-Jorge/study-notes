package com.team001.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.common.CopyUtils;
import com.team001.common.lang.Result;
import com.team001.entity.Room;
import com.team001.entity.RoomType;
import com.team001.service.RoomService;
import com.team001.service.RoomTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@RestController
@RequestMapping("/room-type")
public class RoomTypeController {

    @Autowired
    RoomTypeService roomTypeService;

    @Autowired
    RoomService roomService;

    @GetMapping("/list")
    @ApiOperation(value = "会议室类型列表")
    public Result list(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10000") Integer limit){

        if(page == -1){
            return Result.success(roomTypeService.list());
        }
        Page roomType = new Page<>(page, limit);
        IPage data = roomTypeService.page(roomType, new QueryWrapper<RoomType>().orderByDesc("create_time"));

        return Result.success(data);
    }

    @GetMapping("/get/{id}")
    @ApiOperation(value = "查询当前会议室类型下的会议室")
    public Result getMeetTypeById(@PathVariable("id") Integer id){

        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tno",id);
        queryWrapper.orderByDesc("create_time");
        List<Room> data = roomService.list(queryWrapper);
        return Result.success(data);
    }

    @GetMapping("/getDetail/{id}")
    @ApiOperation(value = "根据id查询会议室类型")
    public Result getDetail(@PathVariable("id") Integer id){


        RoomType room = roomTypeService.getById(id);
        return Result.success(room);
    }

    @ApiOperation("会议室类型上传或更新")
    @PostMapping("/add")
    @Transactional
    public Result save(@Validated @RequestBody RoomType roomType){

        RoomType temp = null;
        /*id存在表示更新会议室*/
        if(roomType.getTno()!=null){
            temp = roomTypeService.getById(roomType.getTno());
        } else { //id不存在，创建会议室
            temp = new RoomType();
            temp.setCreateTime(LocalDateTime.now());
            //其他数据由前端传过来
        }
        CopyUtils.copyProperties(roomType,temp);
        boolean b = roomTypeService.saveOrUpdate(temp);
        if(b){
            return Result.success(0,"保存成功",null);
        } else {
            return Result.fail(-1,"保存失败",null);
        }
    }

    @GetMapping("/del/{id}")
    @ApiOperation(value = "根据id查询会议室类型")
    public Result del(@PathVariable("id") Integer id){


        boolean b = roomTypeService.removeById(id);
        if(b){
            return Result.success("删除成功");
        } else {
            return Result.fail("删除失败");

        }
    }
}
