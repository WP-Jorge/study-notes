package cn.cslg.applysystem.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.sql.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@ApiModel(value="ArticleFile对象", description="")
public class ArticleFile implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "文章文件id")
    @TableId(value = "article_file_id", type = IdType.AUTO)
    private Integer articleFileId;

    @ApiModelProperty(value = "文章id")
    private Integer articleId;

    @ApiModelProperty(value = "文件id")
    private Integer fileId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
