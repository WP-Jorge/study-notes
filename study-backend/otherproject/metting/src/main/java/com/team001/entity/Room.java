package com.team001.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

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
@TableName("m_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会议室编号
     */
    @TableId(value = "mno", type = IdType.AUTO)
    private Integer mno;

    /**
     * 会议室类型名称
     */
    @NotBlank(message = "会议室类型名称不能为空")
    private String typeName;


    /**
     * 容纳人数
     */
    @NotBlank(message = "容纳人数不能为空")
    private Integer contain;

    /**
     * 会议地点
     */
    @NotBlank(message = "会议地点不能为空")
    private String position;

    /**
     * 会议室图片
     */
    private String pic;

    /**
     * 会议室名称
     */
    @NotBlank(message = "会议室名称不能为空")
    private String name;

    /**
     * 设备简介
     */
    @NotBlank(message = "设备简介不能为空")
    private String device;

    /**
     * 会议室描述
     */
    @NotBlank(message = "会议室描述不能为空")
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 是否开启：1开启，0关闭
     */
    private Integer enabled;


}
