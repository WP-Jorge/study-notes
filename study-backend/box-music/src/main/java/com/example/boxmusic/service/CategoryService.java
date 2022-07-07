package com.example.boxmusic.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddCategoryDTO;
import com.example.boxmusic.pojo.dto.UpdateCategoryDTO;
import com.example.boxmusic.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.boxmusic.utils.R;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
public interface CategoryService extends IService<Category> {
	
	R getCategoriesByCategoryNamePage(Page<Map<String, Object>> page, String categoryName);
	
	R deleteCategoriesByCategoryIds(List<Long> categoryIds);
	
	R addCategory(AddCategoryDTO addCategoryDTO);
	
	R updateCategory(MultipartFile picture, UpdateCategoryDTO updateCategoryDTO);
}
