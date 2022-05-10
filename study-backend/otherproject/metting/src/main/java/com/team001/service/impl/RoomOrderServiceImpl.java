package com.team001.service.impl;

import com.team001.entity.RoomOrder;
import com.team001.mapper.RoomOrderMapper;
import com.team001.service.RoomOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team001.vo.RoomOrderView;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@Service
public class RoomOrderServiceImpl extends ServiceImpl<RoomOrderMapper, RoomOrder> implements RoomOrderService {

    @Override
    public List<RoomOrderView> selectRoomList() {
        return this.baseMapper.selectRoomList();
    }
}
