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
@ApiModel(value="RoleApi对象", description="")
public class SaveRoleApiDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "角色id不能为空")
    @ApiModelProperty(value = "角色id")
    private Integer roleId;
    
    @NotNull(message = "权限id不能为空")
    @ApiModelProperty(value = "权限id")
    private Integer apiId;

}
