package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.File;
import cn.cslg.applysystem.mapper.FileMapper;
import cn.cslg.applysystem.service.FileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Development team
 * @since 2021-03-21
 */
@Service
public class FileServiceImpl extends ServiceImpl<FileMapper, File> implements FileService {
	
	@Override
	public int updateFIleRefCountByFileIdSub(Integer fileId) {
		return baseMapper.updateFIleRefCountByFileIdSub(fileId);
	}
}
