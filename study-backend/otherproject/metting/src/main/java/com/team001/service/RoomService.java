package com.team001.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.entity.Room;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team001.vo.RoomVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface RoomService extends IService<Room> {

    Page pageQuery(Page<RoomVo> pageParam);

    RoomVo getRoomById(Integer mno);


}
