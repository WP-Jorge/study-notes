package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PlaylistWithMusicVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long playlistId;

    private String playlistName;
    
    private String playlistPic;
    
    private String playlistDescription;
    
    private Integer totalViews;
    
    private Integer opened;
    
    private PureUserVO user;
    
    private List<CategoryVO> categories;

    private List<MusicVO> musics;
    
}
