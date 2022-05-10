package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;
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
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="UpdateCompeteDTO对象", description="接收由前端传输到后端的Compete数据对象")
public class UpdateCompeteDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "竞赛id不能为空")
    @ApiModelProperty(value = "竞赛id")
    private Integer competeId;
    
    @NotBlank(message = "竞赛名称不能为空")
    @Size(min = 4, max = 25, message = "竞赛名称长度必须在4-25之间")
    @ApiModelProperty(value = "竞赛名称")
    private String competeName;
    
    @NotEmpty(message = "所属院系不能为空")
    @ApiModelProperty(value = "所属院系，每个院系的管理员只能管理自己院系的竞赛")
    private List<Integer> deptIdList;
    
    @NotNull(message = "竞赛级别不能为空")
    @ApiModelProperty(value = "竞赛级别")
    private Integer competeLevId;
    
    @NotNull(message = "竞赛时间不能为空")
    // @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @Pattern(regexp = "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "competeTime的日期格式应为：2021-01-01 00:00:00")
    // @Pattern(regexp = "/^20[0-9]{2}-(0[0-9]{1}-)$/", message = "asd")
    // @Past(message = "时间错误")
    @ApiModelProperty(value = "竞赛时间")
    private String competeTime;
    
    @Min(value = -0, message = "参赛总费用（万元）格式错误")
    @ApiModelProperty(value = "参赛总费用（万元）")
    private Double competeCost;
    
    @NotNull(message = "申请截止时间不能为空")
    // @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    // @Past(message = "时间错误")
    @Pattern(regexp = "^(\\d{4})-(0\\d{1}|1[0-2])-(0\\d{1}|[12]\\d{1}|3[01]) (0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])$", message = "competeEnd的日期格式应为：2021-01-01 00:00:00")
    // @Pattern(regexp = "/^2021-03-30 14:00:00$/", message = "asdasd")
    @ApiModelProperty(value = "申请截止时间")
    private String competeEnd;
    
    @NotBlank(message = "主办方不能为空")
    @Size(min = 2, max = 25, message = "主办方长度必须在2-25之间")
    @ApiModelProperty(value = "主办方")
    private String organizer;

}
