package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApiVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long apiId;
    
    private Long parentApiId;

    private String apiName;

    private String apiPath;
    
    private Integer apiType;

    private String apiMethod;

}
