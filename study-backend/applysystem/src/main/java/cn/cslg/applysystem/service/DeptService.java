package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.dto.AddDeptDTO;
import cn.cslg.applysystem.pojo.entity.Dept;
import cn.cslg.applysystem.pojo.vo.DeptVO;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface DeptService extends IService<Dept> {
	
	Page<DeptVO> getAllDeptsWithPage(Page page);
	
	List<DeptVO> getAllDepts();
	
	int addDept(AddDeptDTO addDeptDTO);
}
