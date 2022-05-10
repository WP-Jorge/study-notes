package com.team001.mapper;

import com.team001.entity.Api;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team001
 * @since 2020-08-15
 */
public interface ApiMapper extends BaseMapper<Api> {
    /*SELECT DISTINCT c.api_url from m_role_api c WHERE c.role_name
    in (SELECT b.role_name from m_user a,m_user_role b
where a.username = b.username
        and a.enabled = 1
        and a.username="092718133");*/
    @Select("select DISTINCT c.api_url from m_role_api c where c.role_name \n" +
            "    in (SELECT b.role_name from m_user a,m_user_role b\n" +
            "where a.username = b.username\n" +
            "        and a.enabled = 1\n" +
            "        and a.username=#{username})")
    List<Api> getApiUrlByUserName(String username);

}
