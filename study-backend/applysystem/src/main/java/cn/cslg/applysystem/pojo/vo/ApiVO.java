package cn.cslg.applysystem.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ApiVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer apiId;
    
    private Integer parentApiId;

    private String apiName;

    private String apiPath;
    
    private Integer apiType;

    private String apiMethod;

}
