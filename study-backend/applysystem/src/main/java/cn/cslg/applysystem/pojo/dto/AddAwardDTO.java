package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
@ApiModel(value="AwardDTO对象", description="接收由前端传输到后端的Award数据对象")
public class AddAwardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "奖项名称不能为空")
    @Size(min = 2, max = 20, message = "奖项名称长度必须为2-20")
    @ApiModelProperty(value = "奖项")
    private String awardName;

}
