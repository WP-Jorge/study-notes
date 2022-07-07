package com.example.boxmusic.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddCategoryDTO对象", description="接收由前端传输到后端的 Category 数据对象")
public class AddCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "分类名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{2,30})$", message = "分类名格式错误")
    @ApiModelProperty(value = "分类名")
    private String categoryName;
    
    @NotBlank(message = "分类类型不能为空")
    @ApiModelProperty(value = "分类类型")
    private Integer categoryType;
    
}
