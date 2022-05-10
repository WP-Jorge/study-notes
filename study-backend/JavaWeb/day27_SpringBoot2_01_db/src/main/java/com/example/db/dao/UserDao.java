package com.example.db.dao;

import com.example.db.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface UserDao {
	User getUser();
}
