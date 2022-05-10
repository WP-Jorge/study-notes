package com.example.boxmusic.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long roleId;

    private String roleName;

}
