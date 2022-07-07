package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateAlbumDTO对象", description="接收由前端传输到后端的 Album 数据对象")
public class UpdateAlbumDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "专辑id不能为空")
    @ApiModelProperty("专辑id")
    @TableId(value = "album_id", type = IdType.AUTO)
    private Long albumId;

    @NotBlank(message = "专辑名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "专辑名格式错误")
    @ApiModelProperty(value = "专辑名")
    private String albumName;
    
    @ApiModelProperty(value = "专辑介绍")
    private String albumDescription;
    
}
