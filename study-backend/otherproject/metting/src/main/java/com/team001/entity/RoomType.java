package com.team001.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_room_type")
public class RoomType implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "tno", type = IdType.AUTO)
    private Integer tno;

    /**
     * 类型名称
     */
    private String roomTypeName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;


}
