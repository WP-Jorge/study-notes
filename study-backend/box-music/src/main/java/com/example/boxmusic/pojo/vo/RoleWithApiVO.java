package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class RoleWithApiVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;
    
    private String roleName;
    
    private List<ApiTreeVO> children;

}
