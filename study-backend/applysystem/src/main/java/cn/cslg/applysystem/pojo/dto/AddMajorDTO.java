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
import javax.validation.constraints.Pattern;
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
@ApiModel(value="AddMajorDTO对象", description="接收由前端传输到后端的Major数据对象")
public class AddMajorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "专业号不能为空")
    @Pattern(regexp = "^(Z[0-9]{3})|(ZB[0-9]{2})|([0-9]{4})$", message = "专业号格式错误")
    @ApiModelProperty(value = "专业号")
    private String majorNo;

    @NotBlank(message = "专业名称不能为空")
    @Length(min = 4, max = 15, message = "专业名称长度必须为4-15位")
    @ApiModelProperty(value = "专业名称")
    private String majorName;
    
    @NotNull(message = "院系id不能为空")
    @ApiModelProperty(value = "院系id")
    private String deptId;

}
