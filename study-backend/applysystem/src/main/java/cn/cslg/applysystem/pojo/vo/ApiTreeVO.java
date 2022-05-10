package cn.cslg.applysystem.pojo.vo;

import lombok.Data;

import java.io.Serializable;
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
public class ApiTreeVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer apiId;
    
    private Integer parentApiId;

    private String apiName;

    private String apiPath;
    
    private Integer apiType;

    private String apiMethod;
    
    private List<ApiTreeVO> childrenApi;

}
