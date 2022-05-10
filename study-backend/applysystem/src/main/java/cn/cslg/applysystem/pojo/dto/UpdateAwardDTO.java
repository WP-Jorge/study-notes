package cn.cslg.applysystem.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="AwardDTO对象", description="接收由前端传输到后端的Award数据对象")
public class UpdateAwardDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "奖项id不能为空")
    @ApiModelProperty(value = "竞赛id")
    private Integer awardId;

    @NotBlank(message = "奖项名称不能为空")
    @ApiModelProperty(value = "奖项")
    private String awardName;

}
