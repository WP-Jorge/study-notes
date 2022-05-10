package cn.cslg.applysystem.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

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
@ApiModel(value="AddDeptDTO对象", description="接收由前端传输到后端的Dept数据对象")
public class UpdateDeptDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "院系id不能为空")
    @ApiModelProperty("院系id")
    private Integer deptId;

    @NotBlank(message = "院系名称不能为空")
    @Length(min = 4, max = 15, message = "院系名称长度必须为4-15位")
    @ApiModelProperty("院系名称")
    private String deptName;

}
