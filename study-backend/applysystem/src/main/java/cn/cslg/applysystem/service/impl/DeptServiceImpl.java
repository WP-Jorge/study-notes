package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.dto.AddDeptDTO;
import cn.cslg.applysystem.pojo.entity.Dept;
import cn.cslg.applysystem.mapper.DeptMapper;
import cn.cslg.applysystem.service.DeptService;
import cn.cslg.applysystem.pojo.vo.DeptVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
	
	@Override
	public Page<DeptVO> getAllDeptsWithPage(Page page) {
		return baseMapper.getAllDeptsWithPage(page);
	}
	
	@Override
	public List<DeptVO> getAllDepts() {
		return baseMapper.getAllDepts();
	}
	
	@Override
	public int addDept(AddDeptDTO addDeptDTO) {
		return baseMapper.addDept(addDeptDTO);
	}
}
