package cn.cslg.applysystem.pojo.vo;

import lombok.Data;

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
public class FileVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer fileId;

    private String filePath;

    private String fileType;

}
