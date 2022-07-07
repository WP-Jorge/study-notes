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
@ApiModel(value="AddSingerDTO对象", description="接收由前端传输到后端的 Singer 数据对象")
public class AddSingerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "歌手名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{2,30})$", message = "歌手名格式错误")
    @ApiModelProperty(value = "歌手名")
    private String singerName;
    
    @ApiModelProperty(value = "歌手描述")
    private String singerDescription;
    
}
