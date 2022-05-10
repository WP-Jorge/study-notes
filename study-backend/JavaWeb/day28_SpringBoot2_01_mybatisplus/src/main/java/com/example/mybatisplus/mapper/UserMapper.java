package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.domain.User;
import org.springframework.stereotype.Repository;

/**
 * 只需要Mapper继承BaseMapper就可以拥有crud能力
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
