package cn.cslg.applysystem.service.impl;

import cn.cslg.applysystem.pojo.entity.Log;
import cn.cslg.applysystem.mapper.LogMapper;
import cn.cslg.applysystem.service.LogService;
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
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService {

}
