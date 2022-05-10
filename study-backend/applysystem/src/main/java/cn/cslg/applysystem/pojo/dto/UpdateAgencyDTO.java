package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@ApiModel(value="Agency对象", description="接收由前端传输到后端的Agency数据对象")
public class UpdateAgencyDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "颁发机构id不能为空")
    @ApiModelProperty(value = "颁发机构id")
    @TableId(value = "agency_id", type = IdType.AUTO)
    private Integer agencyId;

    @NotBlank(message = "颁发机构名称不能为空")
    @Size(min = 2, max = 20, message = "颁发机构名称长度必须为2-20")
    @ApiModelProperty(value = "颁发机构名称")
    private String agencyName;

}
