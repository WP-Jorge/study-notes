package com.team001.service;

import com.team001.entity.Api;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author team001
 * @since 2020-08-15
 */
public interface ApiService extends IService<Api> {
    List<Api> getApiUrlByUserName(String username);

}
