package com.team001.service;

import com.team001.entity.RoomOrder;
import com.baomidou.mybatisplus.extension.service.IService;
import com.team001.vo.RoomOrderView;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface RoomOrderService extends IService<RoomOrder> {

    List<RoomOrderView> selectRoomList();

}
