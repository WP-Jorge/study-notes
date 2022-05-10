package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="AddApiDTO对象", description="接收由前端传输到后端的Api数据对象")
public class UpdateApiDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "资源id不能为空")
    @ApiModelProperty(value = "资源id")
    @TableId(value = "api_id", type = IdType.AUTO)
    private Integer apiId;
    
    @NotNull(message = "父级资源id不能为空")
    @ApiModelProperty(value = "父级资源id")
    private Integer parentApiId;
    
    @NotBlank(message = "资源名称不能为空")
    @ApiModelProperty(value = "资源名称")
    private String apiName;

    @ApiModelProperty(value = "资源路径")
    private String apiPath;

    @ApiModelProperty(value = "请求方法")
    private String apiMethod;
    
    @NotNull(message = "资源类型不能为空")
    @ApiModelProperty(value = "资源类型")
    private Integer apiType;

}
