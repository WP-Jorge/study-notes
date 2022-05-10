package com.team001.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.entity.Room;
import com.team001.mapper.RoomMapper;
import com.team001.service.RoomService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team001.vo.RoomVo;
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
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {

    @Override
    public Page pageQuery(Page<RoomVo> page) {
        List<RoomVo> records = baseMapper.selectRoomList(page);
        page.setRecords(records);
        page.setTotal(records.size());
        return page;
    }

    @Override
    public RoomVo getRoomById(Integer mno) {
        return baseMapper.getRoomById(mno);
    }



}
