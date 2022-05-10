package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddMajorDTO;
import cn.cslg.applysystem.pojo.dto.UpdateMajorDTO;
import cn.cslg.applysystem.pojo.entity.Major;
import cn.cslg.applysystem.service.MajorService;
import cn.cslg.applysystem.utils.R;
import cn.cslg.applysystem.pojo.vo.MajorVO;
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
@RequestMapping("/major")
public class MajorController {
	
	@Autowired
	private MajorService majorService;
	
	@ApiOperation("获取全部专业")
	@GetMapping("/getAllMajorsWithPage")
	public R getAllMajorsWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                           @RequestParam(defaultValue = "10") Integer pageSize) {
		Page<Map> page = new Page<>(currentPage, pageSize);
		Page<MajorVO> majors = majorService.getAllMajorsWithPage(page);
		if (majors.getSize() != 0) {
			return R.ok("majors", Collections.singletonList(majors));
		} else {
			return R.ok("暂无专业信息");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加专业")
	@PostMapping("/addMajor")
	public R addMajor(@Valid @RequestBody AddMajorDTO addMajorDTO) {
		try {
			Major major = new Major();
			major.setMajorNo(addMajorDTO.getMajorNo());
			major.setMajorName(addMajorDTO.getMajorName());
			major.setDeptId(addMajorDTO.getDeptId());
			boolean isSaved = majorService.save(major);
			if (isSaved) {
				return R.ok("添加专业成功");
			}
			return R.ok("添加专业失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加专业失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是院系id错误，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量添加专业")
	@PostMapping("/addMajorList")
	public R addMajorList(@Valid @RequestBody List<AddMajorDTO> majorDTOList) {
		try {
			ArrayList<Major> majorList = new ArrayList<>();
			for (AddMajorDTO addMajorDTO : majorDTOList) {
				Major major = new Major();
				major.setMajorNo(addMajorDTO.getMajorNo());
				major.setMajorName(addMajorDTO.getMajorName());
				major.setDeptId(addMajorDTO.getDeptId());
				majorList.add(major);
			}
			boolean isSaved = majorService.saveBatch(majorList);
			if (isSaved) {
				return R.ok("批量添加专业成功");
			}
			return R.ok("批量添加专业失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("批量添加专业失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是院系id错误，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("根据专业id删除专业")
	@DeleteMapping("/deleteMajorByMajorId")
	public R deleteMajorByMajorId(@PathParam("majorId") Integer majorId) {
		try {
			boolean isRemoved = majorService.removeById(majorId);
			if (isRemoved) {
				return R.ok("删除专业成功");
			}
			return R.ok("删除专业失败，可能是专业id错误");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是该专业id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新专业")
	@PostMapping("/updateMajor")
	public R updateMajor(@Valid @RequestBody UpdateMajorDTO updateMajorDTO) {
		try {
			Major major = new Major();
			major.setMajorId(updateMajorDTO.getMajorId());
			major.setMajorNo(updateMajorDTO.getMajorNo());
			major.setMajorName(updateMajorDTO.getMajorName());
			major.setDeptId(updateMajorDTO.getDeptId());
			boolean isUpdated = majorService.updateById(major);
			if (isUpdated) {
				return R.ok("更新专业成功");
			}
			return R.ok("更新专业失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新专业失败，键名重复，请检查数据是否正确");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是院系id错误，请检查数据是否正确");
		}
	}
	
}

