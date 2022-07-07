package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.Singer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateMusicDTO对象", description="接收由前端传输到后端的 Music 数据对象")
public class UpdateMusicDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "音乐id不能为空")
    @ApiModelProperty("音乐id")
    @TableId(value = "music_id", type = IdType.AUTO)
    private Long musicId;
    
    @NotBlank(message = "音乐名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "音乐名格式错误")
    @ApiModelProperty(value = "音乐名")
    private String musicTitle;
    
    @NotNull(message = "专辑id不能为空")
    @ApiModelProperty("专辑id")
    @TableId(value = "album_id", type = IdType.AUTO)
    private Long albumId;
    
    @ApiModelProperty(value = "歌词")
    private String lyric;
    
    @NotEmpty(message = "分类不能为空")
    @ApiModelProperty(value = "分类")
    private List<Category> categoryList;
    
    @NotEmpty(message = "歌手不能为空")
    @ApiModelProperty(value = "歌手")
    private List<Singer> singerList;
    
}
