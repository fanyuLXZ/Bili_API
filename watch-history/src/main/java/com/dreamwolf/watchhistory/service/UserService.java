package com.dreamwolf.watchhistory.service;

import com.dreamwolf.watchhistory.entities.User;
import org.apache.ibatis.annotations.Param;


public interface UserService {
    public User getUseruID(@Param("uID") int id);
}
