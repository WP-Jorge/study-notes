package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boxmusic.pojo.entity.Category;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdatePlaylistAdminDTO对象", description="接收由前端传输到后端的 Playlist 数据对象")
public class UpdatePlaylistAdminDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "歌单id不能为空")
    @ApiModelProperty("歌单id")
    @TableId(value = "playlist_id", type = IdType.AUTO)
    private Long playlistId;
    
    @NotBlank(message = "歌单名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "歌单名格式错误")
    @ApiModelProperty(value = "歌单名")
    private String playlistName;
    
    @NotBlank(message = "歌单描述不能为空")
    @ApiModelProperty(value = "歌单描述")
    private String playlistDescription;
    
    @ApiModelProperty(value = "分类")
    private List<Category> categoryList;
    
}
