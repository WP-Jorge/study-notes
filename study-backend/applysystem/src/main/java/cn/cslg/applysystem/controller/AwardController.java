package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddAwardDTO;
import cn.cslg.applysystem.pojo.dto.UpdateAwardDTO;
import cn.cslg.applysystem.pojo.entity.Agency;
import cn.cslg.applysystem.pojo.entity.Award;
import cn.cslg.applysystem.pojo.vo.AwardVO;
import cn.cslg.applysystem.service.AwardService;
import cn.cslg.applysystem.utils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/award")
@Validated
public class AwardController {
	
	@Autowired
	private AwardService awardService;
	
	@ApiOperation("获取所有奖项")
	@GetMapping("/getAllAwardsWithPage")
	public R getAllAwardWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                             @RequestParam(defaultValue = "10") Integer pageSize) {
		Page page = new Page(currentPage, pageSize);
		IPage<AwardVO> awardWithPage = awardService.getAllAwardWithPage(page);
		return R.ok("awards", Collections.singletonList(awardWithPage));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加奖项")
	@PostMapping("/addAward")
	public R addAward(@Valid @RequestBody AddAwardDTO addAwardDTO) {
		try {
			Award award = new Award();
			award.setAwardName(addAwardDTO.getAwardName());
			boolean save = awardService.save(award);
			if (save) {
				return R.ok("添加奖项成功");
			}
			return R.ok("添加奖项失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加奖项失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新奖项")
	@PostMapping("/updateAward")
	public R updateAward(@Valid @RequestBody UpdateAwardDTO updateAwardDTO) {
		try {
			Award award = new Award();
			award.setAwardId(updateAwardDTO.getAwardId());
			award.setAwardName(updateAwardDTO.getAwardName());
			boolean update = awardService.updateById(award);
			if (update) {
				return R.ok("更新奖项成功");
			}
			return R.ok("更新奖项失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新奖项失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("删除奖项")
	@DeleteMapping("/deleteAwardById")
	public R deleteAwardById(@PathParam("awardId") Integer awardId) {
		try {
			boolean remove = awardService.removeById(awardId);
			if (remove) {
				return R.ok("删除奖项成功");
			}
			return R.ok("删除奖项失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是奖项id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除奖项")
	@DeleteMapping("/deleteAwardsByIds")
	public R deleteAwardsByIds(@RequestBody List<Integer>awardIds) {
		try {
			boolean remove = awardService.removeByIds(awardIds);
			if (remove) {
				return R.ok("批量删除奖项成功");
			}
			return R.ok("批量删除奖项失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是奖项id被引用，请检查数据是否正确");
		}
	}
	
	@ApiOperation("根据奖项名称模糊查询奖项")
	@GetMapping("/getAwardByAwardNameWithPage")
	public R getAwardByAwardNameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                             @RequestParam(defaultValue = "10") Integer pageSize,
	                                     String awardName) {
		Page page = new Page(currentPage, pageSize);
		IPage<AwardVO> awardWithPage = awardService.getAwardByAwardNameWithPage(page, awardName);
		return R.ok("awards", Collections.singletonList(awardWithPage));
	}
	
}

