package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
@ApiModel(value="CompeteLev对象", description="")
public class CompeteLev implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "竞赛等级id")
    @TableId(value = "compete_lev_id", type = IdType.AUTO)
    private Integer competeLevId;

    @ApiModelProperty(value = "竞赛等级名称")
    private String competeLevName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
