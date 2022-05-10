package cn.cslg.applysystem.pojo.vo;

import lombok.Data;

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
public class CompeteVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer competeId;

    private String competeName;
    
    private Date competeTime;

    private Double competeCost;

    private Date competeEnd;
    
    private String organizer;
    
    private CompeteLevVO competeLev;
    
    private List<DeptVO> deptList;
    
}
