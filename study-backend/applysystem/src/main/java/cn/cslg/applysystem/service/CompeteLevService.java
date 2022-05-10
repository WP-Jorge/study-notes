package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.CompeteLev;
import cn.cslg.applysystem.pojo.vo.CompeteLevVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface CompeteLevService extends IService<CompeteLev> {
	IPage<CompeteLevVO> getAllCompeteLevsWithPage(Page<Map> page);
	IPage<CompeteLevVO> getCompeteLevsByCompeteLevsNameWithPage(Page page, String competeLevName);
}
