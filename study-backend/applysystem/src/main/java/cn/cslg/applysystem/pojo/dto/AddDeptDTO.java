package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
@ApiModel(value="AddDeptDTO对象", description="接收由前端传输到后端的Dept数据对象")
public class AddDeptDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "院系名称不能为空")
    @Length(min = 4, max = 15, message = "院系名称长度必须为4-15位")
    @ApiModelProperty(value = "院系名称")
    private String deptName;

}
