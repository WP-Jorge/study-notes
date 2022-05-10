package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddCompeteDTO;
import cn.cslg.applysystem.pojo.dto.UpdateCompeteDTO;
import cn.cslg.applysystem.pojo.entity.Compete;
import cn.cslg.applysystem.pojo.entity.CompeteDept;
import cn.cslg.applysystem.pojo.entity.Dept;
import cn.cslg.applysystem.service.CompeteDeptService;
import cn.cslg.applysystem.service.CompeteService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.CompeteVO;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@RequestMapping("/compete")
@Validated
public class CompeteController {
	
	@Autowired
	private CompeteService competeService;
	
	@Autowired
	private CompeteDeptService competeDeptService;
	
	// @GetMapping("/getAllCompetes")
	// public R getAllCompetes() {
	// 	List<CompeteVO> allCompetes = competeService.getAllCompetes();
	// 	HashMap<String, Object> map = new HashMap<>();
	// 	map.put("competes", allCompetes);
	// 	return R.ok(map);
	// }
	
	@ApiOperation("获取全部竞赛")
	@GetMapping("/getAllCompetesWithPage")
	public R getAllCompetesWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                                @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<CompeteVO> competes = competeService.getAllCompetesWithPage(page);
		return R.ok("competes", Collections.singletonList(competes));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加竞赛")
	@PostMapping("/addCompete")
	public R addCompete(@Valid @RequestBody AddCompeteDTO addCompeteDTO) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date competeTime = formatter.parse(addCompeteDTO.getCompeteTime());
			Date competeEnd = formatter.parse(addCompeteDTO.getCompeteEnd());
			
			if (competeEnd.getTime() < competeTime.getTime()) {
				return R.ok("竞赛时间不能晚于申请截止时间");
			}
			Compete compete = new Compete();
			compete.setCompeteName(addCompeteDTO.getCompeteName());
			compete.setCompeteLevId(addCompeteDTO.getCompeteLevId());
			compete.setCompeteTime(addCompeteDTO.getCompeteTime());
			compete.setCompeteCost(addCompeteDTO.getCompeteCost());
			compete.setCompeteEnd(addCompeteDTO.getCompeteEnd());
			compete.setOrganizer(addCompeteDTO.getOrganizer());
			boolean save = competeService.save(compete);
			
			List<Integer> deptIdList = addCompeteDTO.getDeptIdList();
			ArrayList<CompeteDept> competeDeptList = new ArrayList<>();
			for (Integer deptId : deptIdList) {
				CompeteDept competeDept = new CompeteDept();
				competeDept.setCompeteId(compete.getCompeteId());
				competeDept.setDeptId(deptId);
				competeDeptList.add(competeDept);
			}
			boolean saveBatch = competeDeptService.saveBatch(competeDeptList);
			
			if (save && saveBatch) {
				return R.ok("添加竞赛成功");
			}
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加竞赛失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是所属院系id、竞赛级别id错误，请检查数据是否正确");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return R.ok("添加竞赛失败");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新竞赛")
	@PostMapping("/updateCompete")
	public R updateCompete(@Valid @RequestBody UpdateCompeteDTO updateCompeteDTO) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date competeTime = formatter.parse(updateCompeteDTO.getCompeteTime());
			Date competeEnd = formatter.parse(updateCompeteDTO.getCompeteEnd());
			
			if (competeEnd.getTime() < competeTime.getTime()) {
				return R.ok("竞赛时间不能晚于申请截止时间");
			}
			Compete compete = new Compete();
			compete.setCompeteId(updateCompeteDTO.getCompeteId());
			compete.setCompeteName(updateCompeteDTO.getCompeteName());
			compete.setCompeteLevId(updateCompeteDTO.getCompeteLevId());
			compete.setCompeteTime(updateCompeteDTO.getCompeteTime());
			compete.setCompeteCost(updateCompeteDTO.getCompeteCost());
			compete.setCompeteEnd(updateCompeteDTO.getCompeteEnd());
			compete.setOrganizer(updateCompeteDTO.getOrganizer());
			boolean update = competeService.updateById(compete);
			
			List<Integer> deptIdList = updateCompeteDTO.getDeptIdList();
			ArrayList<CompeteDept> competeDeptList = new ArrayList<>();
			for (Integer deptId : deptIdList) {
				CompeteDept competeDept = new CompeteDept();
				competeDept.setCompeteId(compete.getCompeteId());
				competeDept.setDeptId(deptId);
				competeDeptList.add(competeDept);
			}
			QueryWrapper queryWrapper = new QueryWrapper();
			queryWrapper.eq("compete_id", updateCompeteDTO.getCompeteId());
			boolean remove = competeDeptService.remove(queryWrapper);
			boolean saveBatch = competeDeptService.saveBatch(competeDeptList);
			
			if (update && remove && saveBatch) {
				return R.ok("更新竞赛成功");
			}
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新竞赛失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是所属院系id、竞赛级别id错误，请检查数据是否正确");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return R.ok("更新竞赛失败");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据竞赛id删除竞赛")
	@DeleteMapping("/deleteCompeteByCompeteId")
	public R deleteCompeteByCompeteId(@PathParam("competeId") Integer competeId) {
		boolean remove = competeService.removeById(competeId);
		if (remove) {
			return R.ok("删除竞赛成功");
		}
		return R.ok("删除竞赛失败");
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据竞赛id批量删除竞赛")
	@DeleteMapping("/deleteCompetesByCompeteIdList")
	public R deleteCompetesByCompeteIdList(@RequestBody List<Integer> competeIdList) {
		boolean remove = competeService.removeByIds(competeIdList);
		if (remove) {
			return R.ok("批量删除竞赛成功");
		}
		return R.ok("批量删除竞赛失败");
	}
	
	@ApiOperation("根据竞赛名称模糊查询竞赛")
	@GetMapping("/getCompetesByCompeteNameWithPage")
	public R getCompetesByCompeteNameWithPage(@PathParam("competeName") String competeName,
	                                         @RequestParam(defaultValue = "1") Integer currentPage,
	                                         @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		IPage<CompeteVO> competes = competeService.getCompetesByCompeteNameWithPage(page, competeName);
		return R.ok("competes", Collections.singletonList(competes));
	}
}

