package com.example.boxmusic.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.Singer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AddMusicDTO对象", description="接收由前端传输到后端的 Music 数据对象")
public class AddMusicDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "音乐名不能为空")
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "音乐名格式错误")
    @ApiModelProperty(value = "音乐名")
    private String musicTitle;
    
    @ApiModelProperty(value = "歌词")
    private String lyric;
    
    @NotEmpty(message = "分类不能为空")
    @ApiModelProperty(value = "分类")
    private List<Category> categoryList;
    
    @NotEmpty(message = "歌手不能为空")
    @ApiModelProperty(value = "歌手")
    private List<Singer> singerList;
    
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "专辑格式错误")
    @ApiModelProperty(value = "专辑")
    private String album;
    
    @Pattern(regexp = "^([\\u4e00-\\u9fa5\\w]{1,30})$", message = "流派格式错误")
    @ApiModelProperty(value = "流派")
    private String genre;
    
}
