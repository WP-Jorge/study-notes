package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;

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
@ApiModel(value="CompeteDept对象", description="")
public class CompeteDept implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竞赛部门id")
    @TableId(value = "compete_dept_id", type = IdType.AUTO)
    private Integer competeDeptId;

    @ApiModelProperty(value = "竞赛id")
    private Integer competeId;

    @ApiModelProperty(value = "部门id")
    private Integer deptId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
