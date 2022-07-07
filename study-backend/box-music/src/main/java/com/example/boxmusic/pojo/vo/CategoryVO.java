package com.example.boxmusic.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long categoryId;
    
    private Integer categoryType;
    
    private String categoryName;
    
}
