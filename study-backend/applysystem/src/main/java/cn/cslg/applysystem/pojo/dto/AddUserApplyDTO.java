package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
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
@ApiModel(value="UserApply对象", description="")
public class AddUserApplyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户id不能为空")
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @NotNull(message = "成果申报id不能为空")
    @ApiModelProperty(value = "成果申报id")
    private Integer allApplyId;

}
