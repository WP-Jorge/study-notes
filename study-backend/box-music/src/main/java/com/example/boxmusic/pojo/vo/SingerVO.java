package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class SingerVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long singerId;

    private String singerName;
    
    private String singerPic;
    
    private Integer totalViews;

}
