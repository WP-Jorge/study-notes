package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.Compete;
import cn.cslg.applysystem.pojo.vo.CompeteVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
public interface CompeteService extends IService<Compete> {
	// List<CompeteVO> getAllCompetes();
	IPage<CompeteVO> getAllCompetesWithPage(Page page);
	IPage<CompeteVO> getCompetesByCompeteNameWithPage(Page page, String competeName);
}
