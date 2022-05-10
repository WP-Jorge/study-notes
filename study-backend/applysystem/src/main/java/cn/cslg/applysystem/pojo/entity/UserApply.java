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
@ApiModel(value="UserApply对象", description="")
public class UserApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "用户申请id")
    @TableId(value = "user_apply_id", type = IdType.AUTO)
    private Integer userApplyId;

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "成功申报id")
    private Integer allApplyId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
