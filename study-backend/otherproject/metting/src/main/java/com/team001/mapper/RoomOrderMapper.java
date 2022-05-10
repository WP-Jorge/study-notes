package com.team001.mapper;

import com.team001.entity.RoomOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team001.vo.RoomOrderView;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface RoomOrderMapper extends BaseMapper<RoomOrder> {
    @Select(" SELECT COUNT(1) AS total,`name`, MONTH(create_time) AS months FROM m_room_order GROUP BY `name` desc LIMIT 10")
    List<RoomOrderView> selectRoomList();


}
