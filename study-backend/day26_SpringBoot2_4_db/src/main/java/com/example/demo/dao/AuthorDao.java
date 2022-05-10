package com.example.demo.dao;

import com.example.demo.pojo.Author;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface AuthorDao {
	Author getAuthor();
}
