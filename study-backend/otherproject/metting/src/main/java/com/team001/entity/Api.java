package com.team001.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author team001
 * @since 2020-08-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("m_api")
public class Api implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "api_id", type = IdType.AUTO)
    private Integer apiId;

    /**
     * api名称
     */
    private String apiName;

    /**
     * api路径
     */
    private String apiUrl;

    /**
     * 父id
     */
    private Integer apiPid;

    /**
     * 排序
     */
    private Integer apiSort;


}
