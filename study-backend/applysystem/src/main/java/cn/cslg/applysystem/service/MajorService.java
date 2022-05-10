package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.Major;
import cn.cslg.applysystem.pojo.vo.MajorVO;
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
public interface MajorService extends IService<Major> {
	Page<MajorVO> getAllMajorsWithPage(Page page);
	List<MajorVO> getAllMajors();
}
