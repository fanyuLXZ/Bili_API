package com.dreamwolf.fav.service.impl;

import com.dreamwolf.entity.fav.Userfavoritelist;
import com.dreamwolf.fav.mapper.UserfavoritelistMapper;
import com.dreamwolf.fav.service.UserfavoritelistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 用户收藏列表 服务实现类
 * </p>
 *
 * @author 老徐
 * @since 2021-04-14
 */
@Service
public class UserfavoritelistServiceImpl implements UserfavoritelistService {

    @Resource
    private UserfavoritelistMapper userfavoritelistMapper;

    @Override
    public List<Userfavoritelist> selectfavuid(Integer uid) {
        return userfavoritelistMapper.selectfavuid(uid);
    }

    @Override
    public List<Userfavoritelist> selectlist() {
        return userfavoritelistMapper.selectlistshow();
    }

    @Override
    public Integer selectfavListID(Integer favListID) {
        return userfavoritelistMapper.selectfavListID(favListID);
    }
}
