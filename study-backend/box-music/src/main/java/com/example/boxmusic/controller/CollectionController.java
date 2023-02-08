package com.example.boxmusic.controller;


import com.alibaba.fastjson.JSON;
import com.example.boxmusic.pojo.dto.UpdateCollectionDTO;
import com.example.boxmusic.pojo.dto.UpdateRoleApiDTO;
import com.example.boxmusic.service.CollectionService;
import com.example.boxmusic.service.UserService;
import com.example.boxmusic.utils.R;
import com.example.boxmusic.utils.Value;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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
@RequestMapping("/collection")
public class CollectionController {
	@Autowired
	private CollectionService collectionService;

	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量添加收藏歌曲")
	@PostMapping("/addMusicToCollection")
	public R addMusicToCollection(HttpServletRequest httpServletRequest, @RequestBody @NotEmpty(message = "音乐id不能为空") List<Long> musicIds) {
		if (musicIds.size() == 0) {
			return R.error("音乐id不能为空");
		}
		return collectionService.updateCollection(httpServletRequest.getHeader(Value.HEADER), musicIds);
	}

	@ApiOperation("获取用户收藏")
	@GetMapping("/getCollection")
	public R getCollection(HttpServletRequest httpServletRequest) {
		return collectionService.getCollection(httpServletRequest.getHeader(Value.HEADER));
	}

	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除收藏")
	@DeleteMapping("/deleteCollection")
	public R deleteCollection(HttpServletRequest httpServletRequest, @RequestBody List<Long> musicIds) {
		return collectionService.deleteCollection(httpServletRequest.getHeader(Value.HEADER), musicIds);
	}
}

