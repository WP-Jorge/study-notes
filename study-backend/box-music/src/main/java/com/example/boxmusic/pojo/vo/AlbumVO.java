package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AlbumVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long albumId;

    private String albumName;
    
    private String albumPic;
    
    private String albumDescription;
    
    private Integer totalViews;

}
