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

/**
 * <p>
 * 
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Data
public class InstructorApplyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer instructorId;

    private String instructorName;
    
    private String tel;
    
    private String email;
    
    private String instructorNo;
    
    private Integer applyOrder;
    
    private DeptVO dept;

}
