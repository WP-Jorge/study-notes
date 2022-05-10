package com.team001.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(value = "会议室展示")
public class RoomVo {

    private static final long serialVersionUID = 1L;

    /**
     * 会议室编号
     */
    private Integer mno;

    /**
     * 会议室类型编号
     */
    private Integer tno;

    /**
     * 容纳人数
     */
    private Integer contain;

    /**
     * 会议地点
     */
    private String position;

    /**
     * 会议室图片
     */
    private String pic;

    /**
     * 会议室名称
     */
    private String name;

    /**
     * 设备简介
     */
    private String device;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 会议室类型名称
     */
    private String roomTypeName;
}
