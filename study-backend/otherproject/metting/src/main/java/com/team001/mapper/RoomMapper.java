package com.team001.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team001.entity.Room;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team001.vo.RoomVo;
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
public interface RoomMapper extends BaseMapper<Room> {

    @Select("select * from m_room,m_room_type where m_room.tno=m_room_type.tno")
    List<RoomVo> selectRoomList(Page page);

    @Select("select * from m_room,m_room_type where m_room.mno=#{mno} and m_room.type_name=m_room_type.room_type_name")
    RoomVo getRoomById(Integer mno);


}
