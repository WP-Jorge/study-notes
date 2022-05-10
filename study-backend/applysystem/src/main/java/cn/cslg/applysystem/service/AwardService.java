package cn.cslg.applysystem.service;

import cn.cslg.applysystem.pojo.entity.Award;
import cn.cslg.applysystem.pojo.vo.AwardVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
public interface AwardService extends IService<Award> {
	IPage<AwardVO> getAllAwardWithPage(Page page);
	IPage<AwardVO> getAwardByAwardNameWithPage(Page page, String awardName);
}
