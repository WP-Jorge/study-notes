package com.example.boxmusic.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateCollectionDTO对象", description="接收由前端传输到后端的 Collection 数据对象")
public class UpdateCollectionDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "用户id不能为空")
    @ApiModelProperty("用户id")
    private Long userId;
    
    @NotNull(message = "音乐id不能为空")
    @ApiModelProperty("音乐id")
    private Long musicId;

}
