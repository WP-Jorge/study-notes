package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Api对象", description="")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "资源id")
    @TableId(value = "api_id", type = IdType.AUTO)
    private Integer apiId;
    
    @ApiModelProperty(value = "父级资源id")
    private Integer parentApiId;

    @ApiModelProperty(value = "资源名称")
    private String apiName;

    @ApiModelProperty(value = "资源路径")
    private String apiPath;

    @ApiModelProperty(value = "请求方法")
    private String apiMethod;
    
    @ApiModelProperty(value = "资源类型")
    private Integer apiType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
