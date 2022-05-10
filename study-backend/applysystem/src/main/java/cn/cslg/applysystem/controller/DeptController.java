package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddDeptDTO;
import cn.cslg.applysystem.pojo.dto.UpdateDeptDTO;
import cn.cslg.applysystem.pojo.entity.Dept;
import cn.cslg.applysystem.service.DeptService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.DeptVO;
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
 *  前端控制器
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@RestController
@Validated
@RequestMapping("/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@ApiOperation("获取全部院系")
	@GetMapping("/getAllDeptsWithPage")
	public R getAllDeptsWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                           @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		Page<DeptVO> depts = deptService.getAllDeptsWithPage(page);
		if (depts != null) {
			return R.ok("depts", Collections.singletonList(depts));
		} else {
			return R.ok("暂无院系信息");
		}
	}
	
	// @ApiOperation("获取全部院系")
	// @GetMapping("/getAllDepts")
	// public R getAllDepts() {
	// 	List<DeptVO> depts = deptService.getAllDepts();
	// 	return R.ok("depts", Collections.singletonList(depts));
	// }
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加院系")
	@PostMapping("/addDept")
	public R addDept(@Valid @RequestBody AddDeptDTO addDeptDTO) {
		try {
			int i = deptService.addDept(addDeptDTO);
			if (i > 0) {
				return R.ok("添加院系成功");
			}
			return R.ok("添加院系失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加院系失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量添加院系")
	@PostMapping("/addDeptList")
	public R addDeptList(@Valid @RequestBody List<AddDeptDTO> deptDTOList) {
		try {
			ArrayList<Dept> deptList = new ArrayList<>();
			for (AddDeptDTO addDeptDTO : deptDTOList) {
				Dept dept = new Dept();
				dept.setDeptName(addDeptDTO.getDeptName());
				deptList.add(dept);
			}
			boolean isSaved = deptService.saveBatch(deptList);
			if (isSaved) {
				return R.ok("批量添加院系成功");
			}
			return R.ok("批量添加院系失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("批量添加院系失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据院系id删除院系")
	@DeleteMapping  ("/deleteDeptByDeptId")
	public R deleteDeptByDeptId(@PathParam("deptId") Integer deptId) {
		try {
			boolean isRemoved = deptService.removeById(deptId);
			if (isRemoved) {
				return R.ok("删除院系成功");
			}
			return R.ok("删除院系失败，可能是院系id错误");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是院系id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新院系")
	@PostMapping("/updateDept")
	public R updateDept(@Valid @RequestBody UpdateDeptDTO updateDeptDTO) {
		try {
			Dept dept = new Dept();
			dept.setDeptId(updateDeptDTO.getDeptId());
			dept.setDeptName(updateDeptDTO.getDeptName());
			boolean isUpdated = deptService.updateById(dept);
			if (isUpdated) {
				return R.ok("更新成功");
			}
			return R.ok("更新失败，可能是院系id错误，请检查数据是否正确");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新院系失败，键名重复，请检查数据是否正确");
		}
	}
	
}

