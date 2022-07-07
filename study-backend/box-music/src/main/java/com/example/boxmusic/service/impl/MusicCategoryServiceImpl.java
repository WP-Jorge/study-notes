package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.pojo.entity.MusicCategory;
import com.example.boxmusic.mapper.MusicCategoryMapper;
import com.example.boxmusic.pojo.vo.CategoryVO;
import com.example.boxmusic.pojo.vo.MusicVO;
import com.example.boxmusic.service.MusicCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.utils.R;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class MusicCategoryServiceImpl extends ServiceImpl<MusicCategoryMapper, MusicCategory> implements MusicCategoryService {
	

}
