package cn.cslg.applysystem.pojo.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.sql.Date;
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
@ApiModel(value="AllApply对象", description="接收由前端传输到后端的AllApply数据对象")
public class AddAllApplyDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @NotNull(message = "竞赛id不能为空")
    @ApiModelProperty(value = "申报的竞赛，关联")
    private Integer competeId;
    
    @NotNull(message = "组织单位id不能为空")
    @ApiModelProperty(value = "组织单位（学院/部门）")
    private Integer deptId;
    
    // @NotNull(message = "奖项id不能为空")
    @ApiModelProperty(value = "取得成绩/获奖等级，包括未获奖（null），一等奖，二等奖")
    private Integer awardId;
    
    // @NotNull(message = "获奖时间不能为空")
    // @Past(message = "获奖时间不能为未来的时间")
    @ApiModelProperty(value = "获奖时间（未获奖为空）")
    private Date awardTime;
    
    @NotNull(message = "认证状态不能为空")
    @ApiModelProperty(value = "认证：0 未通过审核，1 院系认证通过，2 教务处认证通过")
    private Integer auth = 0;

    @ApiModelProperty(value = "发证单位")
    // @TableField("agency_Id")
    private Integer agencyId;

    // @ApiModelProperty(value = "电子证书照片，可通过文件表进行系统管理文件（未获奖为空）")
    // private Integer fileId;
    
    @NotNull(message = "请确认选择团队负责人")
    @ApiModelProperty(value = "团队负责人")
    private Integer userId;
    
    @NotNull(message = "请输入选择是团队赛还是个人赛")
    @ApiModelProperty(value = "团队（1） or 个人（0）")
    private Integer isGroup;
    
    @NotNull(message = "请确认是否可修改")
    @ApiModelProperty(value = "修改状态（0：不可修改，1：可修改）")
    private Integer status = 1;

    @Size(min = 0, max = 300, message = "备注长度限制300字")
    @ApiModelProperty(value = "备注")
    private String des;
    
    // @NotNull(message = "参赛人员不能为空")
    @NotEmpty(message = "参赛人员不能为空")
    @ApiModelProperty(value = "参赛人员")
    private List<Integer> studentList;
    
    // @NotNull(message = "指导教师不能为空")
    @NotEmpty(message = "指导教师不能为空")
    @ApiModelProperty(value = "指导教师")
    private List<Integer> teacherList;
    
    @ApiModelProperty(value = "证书图片")
    private MultipartFile file;

}
