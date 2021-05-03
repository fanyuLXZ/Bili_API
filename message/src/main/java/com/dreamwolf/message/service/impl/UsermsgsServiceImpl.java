package com.dreamwolf.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dreamwolf.entity.message.Usermsgs;
import com.dreamwolf.message.mapper.UsermsgsMapper;
import com.dreamwolf.message.service.UsermsgsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户消息表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-23
 */
@Service
public class UsermsgsServiceImpl extends ServiceImpl<UsermsgsMapper, Usermsgs> implements UsermsgsService {

    @Resource
    private UsermsgsMapper usermsgsMapper;

    @Override
    public List<Usermsgs> selectusermsgs(Integer uid) {
        return usermsgsMapper.selectusermsgs(uid);
    }

    @Override
    public List<Usermsgs> selectusmsgsfid(Integer friendID) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("friendID",friendID);
        return usermsgsMapper.selectList(wrapper);
    }

    @Override
    public List<Usermsgs> selectusmsgsuid(Integer userID) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("userID",userID);
        return usermsgsMapper.selectList(wrapper);
    }

    @Override
    public List<Usermsgs> usermsgslistuidfid(Integer userID, Integer friendID) {
        return usermsgsMapper.usermsgslistuidfid(userID,friendID);
    }
}
