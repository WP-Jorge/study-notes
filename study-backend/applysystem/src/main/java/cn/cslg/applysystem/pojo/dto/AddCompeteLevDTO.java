package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Date;

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
@ApiModel(value="AddCompeteLevDTO对象", description="接收由前端传输到后端的CompeteLev数据对象")
public class AddCompeteLevDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotBlank(message = "竞赛等级名称不能为空")
    @Length(min = 2, max = 15, message = "竞赛等级名称长度必须为2-15位")
    @ApiModelProperty(value = "竞赛等级名称")
    private String competeLevName;

}
