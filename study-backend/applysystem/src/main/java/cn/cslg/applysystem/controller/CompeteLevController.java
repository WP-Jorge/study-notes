package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddCompeteLevDTO;
import cn.cslg.applysystem.pojo.dto.UpdateCompeteLevDTO;
import cn.cslg.applysystem.pojo.entity.CompeteLev;
import cn.cslg.applysystem.service.CompeteLevService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.CompeteLevVO;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@Validated
@RequestMapping("/competeLev")
public class CompeteLevController {
	
	@Autowired
	private CompeteLevService competeLevService;
	
	@ApiOperation("获取全部竞赛级别")
	@GetMapping("/getAllCompeteLevsWithPage")
	public R getAllCompeteLevsWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                                   @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<CompeteLevVO> levs = competeLevService.getAllCompeteLevsWithPage(page);
		return R.ok("levs", Collections.singletonList(levs));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加竞赛等级")
	@PostMapping("/addCompeteLev")
	public R addCompeteLev(@Valid @RequestBody AddCompeteLevDTO addCompeteLevDTO) {
		try {
			CompeteLev competeLev = new CompeteLev();
			competeLev.setCompeteLevName(addCompeteLevDTO.getCompeteLevName());
			boolean save = competeLevService.save(competeLev);
			if (save) {
				return R.ok("添加竞赛等级成功");
			}
			return R.ok("添加竞赛等级失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加竞赛等级失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量添加竞赛等级")
	@PostMapping("/addCompeteLevs")
	public R addCompeteLevs(@Valid @RequestBody List<AddCompeteLevDTO> addCompeteLevDTOList) {
		try {
			ArrayList<CompeteLev> competeLevList = new ArrayList<>();
			for (AddCompeteLevDTO addCompeteLevDTO : addCompeteLevDTOList) {
				CompeteLev competeLev = new CompeteLev();
				competeLev.setCompeteLevName(addCompeteLevDTO.getCompeteLevName());
				competeLevList.add(competeLev);
			}
			boolean save = competeLevService.saveBatch(competeLevList);
			if (save) {
				return R.ok("批量添加竞赛等级成功");
			}
			return R.ok("批量添加竞赛等级失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加竞赛等级失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("删除竞赛等级")
	@DeleteMapping("/deleteCompeteLevByCompeteLevId")
	public R deleteCompeteLevByCompeteLevId(@PathParam("competeLevId") Integer competeLevId) {
		try {
			boolean remove = competeLevService.removeById(competeLevId);
			if (remove) {
				return R.ok("删除竞赛等级成功");
			}
			return R.ok("删除竞赛等级失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是该竞赛等级id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除竞赛等级")
	@DeleteMapping("/deleteCompeteLevByCompeteLevIds")
	public R deleteCompeteLevByCompeteLevIds(@RequestBody List<Integer> competeLevIdList) {
		try {
			boolean remove = competeLevService.removeByIds(competeLevIdList);
			if (remove) {
				return R.ok("批量删除竞赛等级成功");
			}
			return R.ok("批量删除竞赛等级失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是该竞赛等级id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新竞赛等级")
	@PostMapping("/updateCompeteLev")
	public R updateCompeteLev(@RequestBody UpdateCompeteLevDTO updateCompeteLevDTO) {
		try {
			CompeteLev competeLev = new CompeteLev();
			competeLev.setCompeteLevId(updateCompeteLevDTO.getCompeteLevId());
			competeLev.setCompeteLevName(updateCompeteLevDTO.getCompeteLevName());
			boolean update = competeLevService.updateById(competeLev);
			if (update) {
				return R.ok("更新竞赛等级成功");
			}
			return R.ok("更新竞赛等级失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新竞赛等级失败，键名重复，请检查数据是否正确");
		}
	}
	
	@ApiOperation("根据竞赛级别名称模糊查询竞赛级别")
	@GetMapping("/getCompeteLevsByCompeteLevsNameWithPage")
	public R getCompeteLevsByCompeteLevsNameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                                   @RequestParam(defaultValue = "10") Integer pageSize,
	                                                 String competeLevName) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<CompeteLevVO> levs = competeLevService.getCompeteLevsByCompeteLevsNameWithPage(page, competeLevName);
		return R.ok("levs", Collections.singletonList(levs));
	}
}

