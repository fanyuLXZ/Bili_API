package com.dreamwolf.watchhistory.dao;

import com.dreamwolf.watchhistory.entities.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserDao {
    public User getUseruID(@Param("uID") int id);
}
