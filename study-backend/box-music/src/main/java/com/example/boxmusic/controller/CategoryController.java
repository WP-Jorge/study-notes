package com.example.boxmusic.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddCategoryDTO;
import com.example.boxmusic.pojo.dto.AddRoleDTO;
import com.example.boxmusic.pojo.dto.UpdateCategoryDTO;
import com.example.boxmusic.pojo.dto.UpdateRoleDTO;
import com.example.boxmusic.service.CategoryService;
import com.example.boxmusic.service.RoleService;
import com.example.boxmusic.utils.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@RestController
@Validated
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
	@ApiOperation("根据分类名模糊查询分类")
	@GetMapping("/getCategoriesByCategoryNamePage")
	public R getCategoriesByCategoryNamePage(@RequestParam(defaultValue = "1") Integer currentPage,
									@RequestParam(defaultValue = "10") Integer pageSize,
									@RequestParam(defaultValue = "") String categoryName) {
		Page<Map<String, Object>> page = new Page<Map<String, Object>>(currentPage, pageSize);
		return categoryService.getCategoriesByCategoryNamePage(page, categoryName);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据分类id批量删除分类")
	@DeleteMapping("/deleteCategoriesByCategoryIds")
	public R deleteCategoriesByCategoryIds(@RequestBody @NotEmpty(message = "分类id不能为空") List<Long> categoryIds) {
		try {
			return categoryService.deleteCategoriesByCategoryIds(categoryIds);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("当前分类下存在歌曲，禁止删除");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加分类")
	@PostMapping("/addCategory")
	public R addCategory(MultipartFile picture, @Valid AddCategoryDTO addCategoryDTO) {
		return categoryService.addCategory(picture, addCategoryDTO);
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新分类")
	@PutMapping("/updateCategory")
	public R updateCategory(MultipartFile picture, @Valid UpdateCategoryDTO updateCategoryDTO) {
		return categoryService.updateCategory(picture, updateCategoryDTO);
	}
}

