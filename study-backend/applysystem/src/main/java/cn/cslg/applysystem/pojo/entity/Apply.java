package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Apply对象", description="")
public class Apply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "申报id")
    @TableId(value = "apply_id", type = IdType.AUTO)
    private Integer applyId;

    @ApiModelProperty(value = "申报的竞赛，关联")
    private Integer competeId;

    @ApiModelProperty(value = "组织单位（学院/部门）")
    private Integer deptId;

    @ApiModelProperty(value = "取得成绩/获奖等级（空或null为未获奖）")
    private Integer awardId;

    @ApiModelProperty(value = "获奖时间（未获奖则为空）")
    private Date awardTime;

    @ApiModelProperty(value = "认证：0 未通过审核，1 院系认证通过，2 教务处认证通过")
    private Integer auth;

    @ApiModelProperty(value = "发证单位")
    @TableField("agency_Id")
    private Integer agencyId;

    @ApiModelProperty(value = "电子证书照片，可通过文件表进行系统管理文件（未获奖为空）")
    private Integer fileId;

    @ApiModelProperty(value = "是否是团队负责人，0不是，1是")
    private Integer isPrincipal;

    @ApiModelProperty(value = "团队 or 个人")
    private Integer isGroup;

    @ApiModelProperty(value = "修改状态（0：不可修改，1：可修改）")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String des;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;

}
