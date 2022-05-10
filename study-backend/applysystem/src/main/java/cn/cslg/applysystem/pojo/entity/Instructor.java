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
@ApiModel(value="Instructor对象", description="")
public class Instructor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "指导老师id")
    @TableId(value = "instructor_id", type = IdType.AUTO)
    private Integer instructorId;

    @ApiModelProperty(value = "指导老师")
    private String instructorName;
    
    @ApiModelProperty(value = "指导老师号")
    private String instructorNo;
    
    @ApiModelProperty(value = "电话号码")
    private String tel;
    
    @ApiModelProperty(value = "邮箱")
    private String eamil;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer deleted;


}
