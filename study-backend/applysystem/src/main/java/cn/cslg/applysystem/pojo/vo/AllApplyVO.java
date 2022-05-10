package cn.cslg.applysystem.pojo.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class AllApplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer allApplyId;

    private CompeteVO compete;

    private DeptVO dept;

    private AwardVO award;

    private Date awardTime;
    
    private Integer auth;

    private AgencyVO agency;

    private FileVO file;

    private Integer isGroup;
    
    private UserVO user;
    
    private Integer status;

    private String des;
    
    private List<UserVO> users;
    
    private List<InstructorApplyVO> instructors;

}
