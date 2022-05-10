package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Major;
import cn.cslg.applysystem.mapper.MajorMapper;
import cn.cslg.applysystem.service.MajorService;
import cn.cslg.applysystem.pojo.vo.MajorVO;
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
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {
	
	@Override
	public Page<MajorVO> getAllMajorsWithPage(Page page) {
		return baseMapper.getAllMajorsWithPage(page);
	}
	
	@Override
	public List<MajorVO> getAllMajors() {
		return baseMapper.getAllMajors();
	}
}
