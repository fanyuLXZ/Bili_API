package com.dreamwolf.watchhistory.service.impl;

import com.dreamwolf.watchhistory.dao.UserDao;
import com.dreamwolf.watchhistory.dao.VideocommentDao;
import com.dreamwolf.watchhistory.entities.User;
import com.dreamwolf.watchhistory.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: wzx
 * @data: 2021/4/14 10:14
 * @version: 1.0
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;
    @Override
    public User getUseruID(int id) {
        return userDao.getUseruID(id);
    }
}
