package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import java.sql.Date;

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
@ApiModel(value="Compete对象", description="")
public class Compete implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竞赛id")
    @TableId(value = "compete_id", type = IdType.AUTO)
    private Integer competeId;

    @ApiModelProperty(value = "竞赛名称")
    private String competeName;

    // @ApiModelProperty(value = "所属院系，每个院系的管理员只能管理自己院系的竞赛")
    // private Integer deptId;

    @ApiModelProperty(value = "竞赛级别")
    private Integer competeLevId;

    @ApiModelProperty(value = "竞赛时间")
    // private Date competeTime;
    private String competeTime;

    @ApiModelProperty(value = "参赛总费用（万元）")
    private Double competeCost;

    @ApiModelProperty(value = "申请截止时间")
    // private Date competeEnd;
    private String competeEnd;
    
    @ApiModelProperty(value = "主办方")
    private String organizer;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
