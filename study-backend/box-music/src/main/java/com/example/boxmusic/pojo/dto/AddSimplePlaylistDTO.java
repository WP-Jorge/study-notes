package com.example.boxmusic.pojo.dto;

import com.example.boxmusic.pojo.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddSimplePlaylistDTO对象", description="接收由前端传输到后端的 Playlist 数据对象")
public class AddSimplePlaylistDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "歌单名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "歌单名格式错误")
    @ApiModelProperty(value = "歌单名")
    private String playlistName;

}
