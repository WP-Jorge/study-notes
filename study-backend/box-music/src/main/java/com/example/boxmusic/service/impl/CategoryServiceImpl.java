package com.example.boxmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.boxmusic.pojo.dto.AddCategoryDTO;
import com.example.boxmusic.pojo.dto.AddUserDTO;
import com.example.boxmusic.pojo.dto.UpdateCategoryDTO;
import com.example.boxmusic.pojo.entity.Category;
import com.example.boxmusic.mapper.CategoryMapper;
import com.example.boxmusic.pojo.entity.Role;
import com.example.boxmusic.pojo.entity.Singer;
import com.example.boxmusic.pojo.entity.User;
import com.example.boxmusic.pojo.vo.CategoryVO;
import com.example.boxmusic.pojo.vo.RoleVO;
import com.example.boxmusic.service.CategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.boxmusic.service.FileService;
import com.example.boxmusic.utils.JsonUtil;
import com.example.boxmusic.utils.R;
import com.github.wujun234.uid.impl.CachedUidGenerator;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jorge
 * @since 2022-02-24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
	
	@Autowired
	JsonUtil jsonUtil;
	
	@Autowired
	FileService fileService;
	
	@Value("${categoryPicturePath}")
	private String categoryPicturePath;
	
	@Autowired
	private CachedUidGenerator cachedUidGenerator;
	
	@Override
	public R getCategoriesByCategoryNamePage(Page<Map<String, Object>> page, String categoryName) {
		QueryWrapper<Category> queryWrapper = new QueryWrapper<>();
		if (!"".equals(categoryName) && categoryName != null) {
			queryWrapper.like("category_name", categoryName);
		}
		Page<Map<String, Object>> categoryPages = baseMapper.selectMapsPage(page, queryWrapper);
		categoryPages.setRecords(jsonUtil.transformPages(categoryPages, CategoryVO.class));
		return R.successPage("获取歌曲分类成功", categoryPages);
	}
	
	@Override
	public R deleteCategoriesByCategoryIds(List<Long> categoryIds) {
		List<Category> categories = baseMapper.selectBatchIds(categoryIds);
		int count = baseMapper.deleteBatchIds(categoryIds);
		if (count <= 0) {
			return R.error("删除歌曲分类失败");
		}
		for (Category category : categories) {
			Boolean aBoolean = fileService.deleteFile(categoryPicturePath, category.getCategoryPic());
			if (!aBoolean) {
				log.warn("删除本地分类图片失败");
			}
		}
		return R.success("删除歌曲分类成功");
	}
	
	@Override
	public R addCategory(MultipartFile picture, AddCategoryDTO addCategoryDTO) {
		try {
			if (picture == null) {
				return R.error("添加失败，请上传图片");
			}
			long uid = cachedUidGenerator.getUID();
			String pictureName = fileService.getFilename(picture, uid);
			Category category = new Category();
			BeanUtils.copyProperties(addCategoryDTO, category);
			category.setCategoryId(uid);
			category.setCategoryPic(pictureName);
			int i = baseMapper.insert(category);
			if (i <= 0) {
				throw new RuntimeException("添加分类失败");
			}
			fileService.uploadFile(picture, categoryPicturePath, pictureName);
			return R.success("添加分类成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加失败，分类名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	@Override
	public R updateCategory(MultipartFile picture, UpdateCategoryDTO updateCategoryDTO) {
		try {
			Category category = baseMapper.selectById(updateCategoryDTO.getCategoryId());
			BeanUtils.copyProperties(updateCategoryDTO, category);
			String pictrueName = category.getCategoryPic();
			String picturefilename = null;
			if (picture != null) {
				picturefilename = fileService.getFilename(picture, updateCategoryDTO.getCategoryId());
				category.setCategoryPic(picturefilename);
			}
			int i = baseMapper.updateById(category);
			if (i <= 0) {
				throw new RuntimeException("更新失败");
			}
			if (picture != null) {
				Boolean deleteImage = fileService.deleteFile(categoryPicturePath, pictrueName);
				if (!deleteImage) {
					log.warn("本地图片不存在");
				}
				fileService.uploadFile(picture, categoryPicturePath, picturefilename);
			}
			return R.success("更新成功");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新失败，分类名已存在");
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
