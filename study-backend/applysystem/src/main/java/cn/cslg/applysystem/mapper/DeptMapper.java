package cn.cslg.applysystem.mapper;

import cn.cslg.applysystem.cache.RedisCache;
import cn.cslg.applysystem.pojo.dto.AddDeptDTO;
import cn.cslg.applysystem.pojo.entity.Dept;
import cn.cslg.applysystem.pojo.vo.DeptVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.CacheNamespace;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@CacheNamespace(implementation = RedisCache.class, eviction = RedisCache.class)
public interface DeptMapper extends BaseMapper<Dept> {
	
	Page<DeptVO> getAllDeptsWithPage(Page page);
	
	List<DeptVO> getAllDepts();
	
	int addDept(AddDeptDTO addDeptDTO);
}
