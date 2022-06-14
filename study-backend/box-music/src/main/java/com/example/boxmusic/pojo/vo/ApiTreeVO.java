package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ApiTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long apiId;
    
    private Long parentApiId;

    private String apiName;

    private String apiPath;
    
    private Integer apiType;

    private String apiMethod;
    
    private List<ApiTreeVO> children;

}
