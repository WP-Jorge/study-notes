package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Article;
import cn.cslg.applysystem.mapper.ArticleMapper;
import cn.cslg.applysystem.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

}
