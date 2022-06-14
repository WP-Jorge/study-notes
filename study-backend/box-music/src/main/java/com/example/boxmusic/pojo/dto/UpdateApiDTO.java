package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateApiDTO对象", description="接收由前端传输到后端的 Api 数据对象")
public class UpdateApiDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty("权限id")
    @TableId(value = "api_id", type = IdType.AUTO)
    private Long apiId;

    @NotBlank(message = "权限名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{2,30})$", message = "权限名格式错误")
    @ApiModelProperty(value = "权限名")
    private String apiName;
    
    @NotBlank(message = "权限路径不能为空")
    @Pattern(regexp = "^(/\\w{1,30})+", message = "权限路径格式错误")
    @ApiModelProperty(value = "权限路径")
    private String apiPath;
    
    @ApiModelProperty(value = "父权限id")
    private Long parentApiId;
    
    @NotNull(message = "权限类型不能为空")
    @Range(min = 0, max = 1, message = "权限类型格式错误，可选 0 或 1 ")
    @ApiModelProperty("权限类型")
    private Integer ApiType;
    
}
