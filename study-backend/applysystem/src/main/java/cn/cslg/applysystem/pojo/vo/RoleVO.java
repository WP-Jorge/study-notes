package cn.cslg.applysystem.pojo.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;

    @NotNull(message = "角色名不能为空")
    private String roleName;

}
