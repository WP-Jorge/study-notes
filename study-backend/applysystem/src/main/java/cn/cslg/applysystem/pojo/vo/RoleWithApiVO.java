package cn.cslg.applysystem.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
public class RoleWithApiVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer roleId;
    
    private String roleName;
    
    private List<ApiTreeVO> childrenApi;

}
