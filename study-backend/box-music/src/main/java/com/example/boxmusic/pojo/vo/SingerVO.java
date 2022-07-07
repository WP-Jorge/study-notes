package com.example.boxmusic.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class SingerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long singerId;

    private String singerName;
    
    private String singerPic;
    
    private String singerDescription;
    
    private Integer totalViews;

}
