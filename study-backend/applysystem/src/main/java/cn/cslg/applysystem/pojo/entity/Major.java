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
@ApiModel(value="Major对象", description="")
public class Major implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "专业id")
    @TableId(value = "major_id", type = IdType.AUTO)
    private Integer majorId;

    @ApiModelProperty(value = "专业号")
    private String majorNo;

    @ApiModelProperty(value = "专业名称")
    private String majorName;
    
    @ApiModelProperty(value = "院系id")
    private String deptId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
