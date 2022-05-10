package com.team001.service.impl;

import com.team001.entity.Api;
import com.team001.mapper.ApiMapper;
import com.team001.service.ApiService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author team001
 * @since 2020-08-15
 */
@Service
public class ApiServiceImpl extends ServiceImpl<ApiMapper, Api> implements ApiService {

    @Override
    public List<Api> getApiUrlByUserName(String username) {
        return baseMapper.getApiUrlByUserName(username);
    }
}
