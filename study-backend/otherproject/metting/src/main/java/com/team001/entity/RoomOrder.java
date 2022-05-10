package com.team001.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
@TableName("m_room_order")
public class RoomOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ono", type = IdType.AUTO)
    private Integer ono;


    /**
     * 会议室名称
     */
    @NotBlank(message = "会议室名称不能为空")
    private String name;

    /**
     * 申请人
     */
    private String username;

    /**
     * 预约时间
     */
    private LocalDateTime createTime;

    /**
     * 审核时间
     */
    private LocalDateTime verifyTime;

    /**
     * 审核状态：1审核中，2审核通过，3：审核不通过，0:超时,-1:预约取消
     */
    private Integer status;

    /**
     * 开始时间(时间戳表示，方便后台计算)
     */
    @NotNull(message = "开始时间不能为空")
    private Long startTime;

    /**
     * 结束时间(时间戳表示，方便后台计算)
     */
    @NotNull(message = "结束时间不能为空")
    private Long endTime;

    /**
     * 用于开始时间和结束时间的展示
     */
    private String startEndTime;

    /**
     * 使用人数
     */
    @DecimalMin(value = "2",message = "使用人数不能小于2")
    private Integer personNumber;

    /**
     * 门禁密码
     */
    private String password;

    /**
     * 拒绝理由
     */
    private String rejectReason;

    /**
     * 申请理由
     */
    private String applyReason;


}
