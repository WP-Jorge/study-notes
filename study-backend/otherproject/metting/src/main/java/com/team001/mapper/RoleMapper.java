package com.team001.mapper;

import com.team001.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author team001
 * @since 2020-08-01
 */
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT c.role_name\n" +
            "from m_user a,\n" +
            "     m_user_role c\n" +
            "where a.username = c.username\n" +
            "     and a.enabled = 1\n"+
            "     and a.username=#{userName}")
    List<String> getRolesByUserName(@Param("userName") String userName);

}
