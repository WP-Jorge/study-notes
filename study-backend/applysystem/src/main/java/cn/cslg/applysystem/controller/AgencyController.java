package cn.cslg.applysystem.controller;


import cn.cslg.applysystem.pojo.dto.AddAgencyDTO;
import cn.cslg.applysystem.pojo.dto.UpdateAgencyDTO;
import cn.cslg.applysystem.pojo.entity.Agency;
import cn.cslg.applysystem.pojo.vo.AgencyVO;
import cn.cslg.applysystem.service.AgencyService;
import cn.cslg.applysystem.utils.R;
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
@RequestMapping("/agency")
public class AgencyController {
	
	@Autowired
	private AgencyService agencyService;
	
	@ApiOperation("获取所有颁发机构")
	@GetMapping("/getAllAgenciesWithPage")
	public R getAllAgenciesWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                                @RequestParam(defaultValue = "10") Integer pageSize) {
		Page page = new Page<>(currentPage, pageSize);
		IPage<AgencyVO> agencies = agencyService.getAllAgenciesWithPage(page);
		return R.ok("agencies", Collections.singletonList(agencies));
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("添加颁发机构")
	@PostMapping("/addAgency")
	public R addAgency(@Valid @RequestBody AddAgencyDTO addAgencyDTO) {
		try {
			Agency agency = new Agency();
			agency.setAgencyName(addAgencyDTO.getAgencyName());
			boolean save = agencyService.save(agency);
			if (save) {
				return R.ok("添加颁发机构成功");
			}
			return R.ok("添加颁发机构失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("添加颁发机构失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("更新颁发机构")
	@PostMapping("/updateAgency")
	public R updateAgency(@Valid @RequestBody UpdateAgencyDTO updateAgencyDTO) {
		try {
			Agency agency = new Agency();
			agency.setAgencyId(updateAgencyDTO.getAgencyId());
			agency.setAgencyName(updateAgencyDTO.getAgencyName());
			boolean update = agencyService.updateById(agency);
			if (update) {
				return R.ok("更新颁发机构成功");
			}
			return R.ok("更新颁发机构失败");
		} catch (DuplicateKeyException e) {
			throw new DuplicateKeyException("更新颁发机构失败，键名重复，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("删除颁发机构")
	@DeleteMapping("/deleteAgencyById")
	public R deleteAgencyById(@PathParam("agencyId") Integer agencyId) {
		try {
			boolean remove = agencyService.removeById(agencyId);
			if (remove) {
				return R.ok("删除颁发机构成功");
			}
			return R.ok("删除颁发机构失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是颁发机构id被引用，请检查数据是否正确");
		}
	}
	
	@Transactional(rollbackFor = Exception.class)
	@ApiOperation("批量删除颁发机构")
	@DeleteMapping("/deleteAgenciesByIds")
	public R deleteAgenciesByIds(@RequestBody List<Integer> agencyIds) {
		try {
			boolean remove = agencyService.removeByIds(agencyIds);
			if (remove) {
				return R.ok("批量删除颁发机构成功");
			}
			return R.ok("批量删除颁发机构失败");
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("数据完整性违规异常，可能是颁发机构id被引用，请检查数据是否正确");
		}
	}
	
	@ApiOperation("根据颁发机构名称模糊查询颁发机构")
	@GetMapping("/getAgenciesByAgencyNameWithPage")
	public R getAgenciesByAgencyNameWithPage(@RequestParam(defaultValue = "1") Integer currentPage,
	                                        @RequestParam(defaultValue = "10") Integer pageSize,
	                                         String agencyName) {
		Page page = new Page<>(currentPage, pageSize);
		IPage<AgencyVO> agencies = agencyService.getAgenciesByAgencyNameWithPage(page, agencyName);
		return R.ok("agencies", Collections.singletonList(agencies));
	}
}

