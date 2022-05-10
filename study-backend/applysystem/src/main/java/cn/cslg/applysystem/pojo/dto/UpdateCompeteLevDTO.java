package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

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
@ApiModel(value="UpdateCompeteLevDTO对象", description="接收由前端传输到后端的CompeteLev数据对象")
public class UpdateCompeteLevDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "专业id不能为空")
    @ApiModelProperty(value = "竞赛等级id")
    private Integer competeLevId;
    
    @NotBlank(message = "竞赛等级名称不能为空")
    @Length(min = 2, max = 15, message = "竞赛等级名称长度必须为2-15位")
    @ApiModelProperty(value = "竞赛等级名称")
    private String competeLevName;

}
