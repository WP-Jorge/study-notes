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
 * @since 2021-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="InstructorApply对象", description="")
public class InstructorApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "指导老师参赛id")
    @TableId(value = "instructor_apply_id", type = IdType.AUTO)
    private Integer instructorApplyId;

    @ApiModelProperty(value = "指导老师id")
    private Integer instructorId;

    @ApiModelProperty(value = "参赛id")
    private Integer allApplyId;
    
    @ApiModelProperty(value = "指导老师次序（0：第一指导老师，依次类推）")
    private Integer applyOrder;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
