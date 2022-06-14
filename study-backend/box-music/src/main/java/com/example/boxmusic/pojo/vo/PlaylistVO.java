package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlaylistVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long playlistId;

    private String playlistName;
    
    private String playlistPic;
    
    private Integer totalViews;
    
    private Integer opened;
    
    private PureUserVO user;

}
