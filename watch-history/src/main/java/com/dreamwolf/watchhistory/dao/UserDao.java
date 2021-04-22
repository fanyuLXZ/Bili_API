package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper
public interface UserDao {
    @Autowired
    public User getUseruID(@Param("uID") int id);
}
