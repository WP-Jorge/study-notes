package com.example.boxmusic.service.impl;

import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.mapper.CategoryMapper;
import com.example.boxmusic.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

}
