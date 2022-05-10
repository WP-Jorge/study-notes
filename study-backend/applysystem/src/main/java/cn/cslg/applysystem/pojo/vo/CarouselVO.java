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
public class CarouselVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer carouselId;

    private FileVO file;
    
}
